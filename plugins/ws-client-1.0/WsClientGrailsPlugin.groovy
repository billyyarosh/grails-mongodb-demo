class WsClientGrailsPlugin {

    def version = "1.0"
    def grailsVersion = "1.1.1 > *"
    def dependsOn = [:]
    def pluginExcludes = [
        'grails-app/conf',
        'grails-app/controllers/**',
        'grails-app/views/**',
        'grails-app/web-app']

    def author = 'Cazacu Mihai'
    def authorEmail = 'cazacugmihai@gmail.com'
    def title = "Web Service Client"
    def description = 'A plugin that allows you to connect to web services (uses CXF).'

    def documentation = "http://grails.org/plugin/ws-client"

}

