/* Copyright 2012 SpringSource.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.jetty

import grails.util.BuildSettings
import grails.util.BuildSettingsHolder
import grails.util.PluginBuildSettings
import grails.web.container.EmbeddableServer

import org.eclipse.jetty.plus.webapp.EnvConfiguration
import org.eclipse.jetty.plus.webapp.PlusConfiguration
import org.eclipse.jetty.server.Connector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ssl.SslSocketConnector
import org.eclipse.jetty.webapp.FragmentConfiguration
import org.eclipse.jetty.webapp.MetaInfConfiguration
import org.eclipse.jetty.webapp.TagLibConfiguration
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.webapp.WebInfConfiguration
import org.eclipse.jetty.webapp.WebXmlConfiguration
import org.springframework.core.io.FileSystemResource
import org.springframework.util.Assert
import org.springframework.util.FileCopyUtils

/**
 * An implementation of the EmbeddableServer interface for Jetty.
 *
 * @author Graeme Rocher
 * @since 1.2
 */
class JettyServer implements EmbeddableServer {

	BuildSettings buildSettings
	WebAppContext context
	Server grailsServer

	protected String keystore
	protected File keystoreFile
	protected String keyPassword

	// These are set from the outside in _GrailsRun
	ConfigObject grailsConfig
	def eventListener

	/**
	 * Creates a new JettyServer for the given war and context path
	 */
	JettyServer(String warPath, String contextPath) {
		this()
		context = new WebAppContext(war: warPath, contextPath: contextPath)
	}

	/**
	 * Constructs a Jetty server instance for the given arguments. Used for inline, non-war deployment.
	 *
	 * @basedir The web application root
	 * @webXml The web.xml definition
	 * @contextPath The context path to deploy to
	 * @classLoader The class loader to use
	 */
	JettyServer(String basedir, String webXml, String contextPath, ClassLoader classLoader) {
		this()
		createStandardContext basedir, webXml, contextPath, classLoader
	}

	protected JettyServer() {
		buildSettings = BuildSettingsHolder.getSettings()

		keystore = "$buildSettings.grailsWorkDir/ssl/keystore"
		keystoreFile = new File(keystore)
		keyPassword = '123456'

		System.setProperty 'org.eclipse.jetty.xml.XmlParser.NotValidating', 'true'

		System.setProperty 'TomcatKillSwitch.active', 'true' // workaround to prevent server exiting
	}

	void start(int port) {
		start DEFAULT_HOST, port
	}

	void start(String host = DEFAULT_HOST, int port = DEFAULT_PORT) {
		assertState()
		configureHttpServer context, port, host
		startServer()
	}

	void startSecure(int port) {
		startSecure DEFAULT_HOST, port, DEFAULT_SECURE_PORT
	}

	void startSecure(String host = DEFAULT_HOST, int httpPort = DEFAULT_PORT, int httpsPort = DEFAULT_SECURE_PORT) {
		assertState()
		configureHttpsServer context, httpPort, httpsPort, host
		startServer()
	}

	void stop() {
		assertState()
		grailsServer.stop()
	}

	void restart() {
		assertState()
		stop()
		start()
	}

	/**
	 * Starts the configured Grails server
	 */
	protected void startServer() {
		eventListener?.event('ConfigureJetty', [grailsServer])
		grailsServer.start()
	}

	/**
	 * Creates a standard WebAppContext from the given arguments
	 */
	protected void createStandardContext(String webappRoot, String webXml,
	                                     String contextPath, ClassLoader classLoader) {
		// Jetty requires a 'defaults descriptor' on the filesystem
		File webDefaults = new File(buildSettings.projectWorkDir, 'webdefault.xml')
		if (!webDefaults.exists()) {
			PluginBuildSettings pluginSettings = new PluginBuildSettings(buildSettings)
			File pluginDir = pluginSettings.getPluginDirForName('jetty').file
			FileCopyUtils.copy new File(pluginDir, 'grails-app/conf/webdefault.xml'), webDefaults
		}

		context = new WebAppContext(webappRoot, contextPath)

		setSystemProperty 'java.naming.factory.url.pkgs', 'org.eclipse.jetty.jndi'
		setSystemProperty 'java.naming.factory.initial', 'org.eclipse.jetty.jndi.InitialContextFactory'

		def configurations = [
			WebInfConfiguration,
			WebXmlConfiguration,
			MetaInfConfiguration,
			FragmentConfiguration,
			EnvConfiguration,
			PlusConfiguration,
			JettyWebXmlConfiguration,
			TagLibConfiguration]*.newInstance()

		def grailsJndi = grailsConfig?.grails?.development?.jetty?.env
		if (grailsJndi) {
			def res = new FileSystemResource(grailsJndi.toString())
			if (res.exists()) {
				EnvConfiguration jndiConfig = configurations[4]
				jndiConfig.jettyEnvXml = res.URL
			}
		}

		context.configurations = configurations
		context.defaultsDescriptor = webDefaults.path
		context.classLoader = classLoader
		context.descriptor = webXml
	}

	protected void setSystemProperty(String name, String value) {
		if (!System.getProperty(name)) {
			System.setProperty name, value
		}
	}

	/**
	 * Configures a new Jetty Server instance for the given WebAppContext
	 */
	protected void configureHttpServer(WebAppContext context, int serverPort = DEFAULT_PORT,
	                                   String serverHost = DEFAULT_HOST) {

		grailsServer = new Server(serverPort)
		if (serverHost) {
			grailsServer.connectors[0].host = serverHost
		}

		grailsServer.handler = context
	}

	/**
	 * Configures a secure HTTPS server
	 */
	protected void configureHttpsServer(WebAppContext context, int httpPort = DEFAULT_PORT,
				int httpsPort = DEFAULT_SECURE_PORT, String serverHost = DEFAULT_HOST ) {

		configureHttpServer context, httpPort, serverHost

		if (!keystoreFile.exists()) {
			createSSLCertificate()
		}

		def secureListener = new SslSocketConnector()
		secureListener.port = httpsPort
		if (serverHost) {
			secureListener.host = serverHost
		}
		secureListener.maxIdleTime = 50000
		secureListener.password = keyPassword
		secureListener.keyPassword = keyPassword
		secureListener.keystore = keystore
		secureListener.needClientAuth = false
		secureListener.wantClientAuth = true
		List connectors = grailsServer.connectors
		connectors << secureListener
		grailsServer.connectors = connectors as Connector[]
	}

	/**
	 * Creates the necessary SSL certificate for running in HTTPS mode
	 */
	protected void createSSLCertificate() {
		println 'Creating SSL Certificate...'
		if (!keystoreFile.parentFile.exists() && !keystoreFile.parentFile.mkdir()) {
			throw new RuntimeException("Unable to create keystore folder: $keystoreFile.parentFile.canonicalPath")
		}

		String[] keytoolArgs = [
			'-genkey',
			'-alias',     'localhost',
			'-dname',     'CN=localhost,OU=Test,O=Test,C=US',
			'-keyalg',    'RSA',
			'-validity',  '365',
			'-storepass', 'key',
			'-keystore',  keystore,
			'-storepass', keyPassword,
			'-keypass',   keyPassword
		]

		getKeyToolClass().main keytoolArgs

		println 'Created SSL Certificate.'
	}

	protected Class<?> getKeyToolClass() {
		try {
			Class.forName 'sun.security.tools.KeyTool'
		}
		catch (ClassNotFoundException e) {
			// no try/catch for this one, if neither is found let it fail
			Class.forName 'com.ibm.crypto.tools.KeyTool'
		}
	}

	protected void assertState() {
		Assert.state context != null, 'The WebAppContext has not been initialized!'
	}
}
