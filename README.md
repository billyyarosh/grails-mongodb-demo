Grails - Demo Project W/ MongoDB, Jetty, and GroovyWS
-------------------------------------------------------

**Application name:** *`grails-mongo-demo`*
**The Goal:** To track and record persons addresses.
 
Web application using:

1. `MongoDB`
2. `Spring Security`
3. `Apache CXF`
4. `Jetty Web Server`
5. `Maven build integration`
 
 The application progresses through a series of tutorials.

Quick Start
============
1. Clone into the repo or [download here](https://github.com/keaplogik/grails-mongodb-demo/zipball/master)
2. cd to dirtectory and run: `mvn grails:run-app`
	* Requires
		* Maven
		* Grails SDK (not necessary if running maven targets)
		* MongoDB installed and running
3. To integrate with intelliJ: `grails integrate-with --intellij`

**Tutorial Links**

1. [Grails Part 1 - Setting up the Project][part-one]
2. [Grails Part 2 - Configuring Web Application Plugins][part-two]
3. [Grails Part 3 - Building a CRUD Application][part-three]

#### About this application
- Project Setup
  - Grails SDK 2.1.1
	- Integration with Maven
	- Integrating with IntelliJ
- Configured Plugins
	- MongoDB
	- Jetty
	- Spring Security Core
	- Apache CXF (WSClient)
- Application Code
	- Simple Domain Model
	- CRUD application using Grails Scaffolding
	- Enhanced UI Design(later)
	- Adding Security(later)
	- Services with GroovyWS(later)


[part-one]: http://keaplogik.blogspot.com/2012/10/grails-setting-up-project-on-maven-with.html
[part-two]: http://keaplogik.blogspot.com/2012/10/grails-part-2-configuring-web.html
[part-three]: http://keaplogik.blogspot.com/2012/10/grails-part-3-building-crud-application.html