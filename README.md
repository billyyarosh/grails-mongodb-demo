Grails - Demo Project W/ MongoDB, Jetty, and GroovyWS
-------------------------------------------------------

**Application name:** *`grails-mongodb-demo`*
**The Goal:** To track and record persons addresses.
 
Web application using:

1. `MongoDB`
2. `TW Bootstrap`
3. `Spring Security`
4. `Apache CXF`
5. `Jetty Web Server`
6. ~~`Maven build integration`~~ => Removed funcionality as it doesn't make the best use case.
 
 The application progresses through a series of tutorials.

Quick Start
============
1. Clone into the repo or [download latest tag v1.1](https://github.com/keaplogik/grails-mongodb-demo/zipball/v1.1)
	* Without spring security [download tag v1.0](https://github.com/keaplogik/grails-mongodb-demo/zipball/v1.2)
2. cd to dirtectory and run: `mvn grails:run-app`
	* Requires
		* ~~Maven~~
		* Grails SDK (not necessary if running maven targets)
		* MongoDB installed and running
3. To integrate with intelliJ: `grails integrate-with --intellij`

**Tutorial Links**

1. [Grails Part 1 - Setting up the Project][part-one]
2. [Grails Part 2 - Configuring Web Application Plugins][part-two]
3. [Grails Part 3 - Building a CRUD Application][part-three]
4. [Grails Part 4: Enhanced UI Design w/ Twitter Bootstrap][part-four]

#### About this application
- Project Setup
  - Grails SDK ~~2.1.1~~ => 2.2.0
	- ~~Integration with Maven~~ => Use grails plugin system exlusively (Look Ma, no Maven.)
	- Integrating with IntelliJ
- Configured Plugins
	- MongoDB
	- Jetty
	- Spring Security Core
	- Apache CXF (WSClient)
- Application Code
	- Simple Domain Model
	- CRUD application using Grails Scaffolding
	- Enhanced UI Design
	- Adding Security
	- Services with GroovyWS - **comming soon**
	- => Add twitter client plugin and several other plugins


[part-one]: http://keaplogik.blogspot.com/2012/10/grails-setting-up-project-on-maven-with.html
[part-two]: http://keaplogik.blogspot.com/2012/10/grails-part-2-configuring-web.html
[part-three]: http://keaplogik.blogspot.com/2012/10/grails-part-3-building-crud-application.html
[part-four]: http://keaplogik.blogspot.com/2012/10/grails-part-4-enhanced-ui-design-w.html
