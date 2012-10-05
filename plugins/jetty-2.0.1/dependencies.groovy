grails.project.work.dir = 'target'
grails.project.docs.output.dir = 'docs/manual' // for gh-pages branch
grails.project.source.level = 1.6

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	dependencies {

		String jettyVersion = '7.6.0.v20120127'

		runtime("org.eclipse.jetty.aggregate:jetty-all:$jettyVersion") {
			transitive = false
		}

		// needed for JSP compilation
		runtime 'org.eclipse.jdt.core.compiler:ecj:3.6.2'
	}

	plugins {
		build(':release:2.0.4', ':rest-client-builder:1.0.2') {
			export = false
		}
	}
}
