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

import grails.web.container.EmbeddableServerFactory
import grails.web.container.EmbeddableServer

/**
 * Creates Jetty servers.
 *
 * @author Graeme Rocher
 * @since 1.2
 */
class JettyServerFactory implements EmbeddableServerFactory {

	EmbeddableServer createInline(String basedir, String webXml, String contextPath, ClassLoader classLoader) {
		new JettyServer(basedir, webXml, contextPath, classLoader)
	}

	EmbeddableServer createForWAR(String warPath, String contextPath) {
		new JettyServer(warPath, contextPath)
	}
}
