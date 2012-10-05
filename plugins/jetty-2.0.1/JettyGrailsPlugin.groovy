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
class JettyGrailsPlugin {
	def version = "2.0.1"
	def grailsVersion = "2.0 > *"
	def scopes = [excludes: 'war']
	def title = "Jetty Plugin"
	def author = "Graeme Rocher"
	def authorEmail = "graeme.rocher@springsource.com"
	def description = 'Makes Jetty the development time container for Grails'
	def documentation = "http://grails.org/plugin/jetty"

	def license = 'APACHE'
	def organization = [name: 'SpringSource', url: 'http://www.springsource.org/']
	def developers = [[name: 'Burt Beckwith', email: 'beckwithb@vmware.com']]
	def issueManagement = [system: 'JIRA', url: 'http://jira.grails.org/browse/GPJETTY']
	def scm = [url: 'https://github.com/grails-plugins/grails-jetty']
}
