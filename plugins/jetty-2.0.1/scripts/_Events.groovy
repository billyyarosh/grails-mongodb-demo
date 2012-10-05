// No programmable web.xml path yet, so put it in the right place automatically
eventGenerateWebXmlEnd = {
	packagePluginsForWar("${basedir}/web-app")
	System.setProperty("grails.server.factory", "org.grails.jetty.JettyServerFactory")
}