grails.servlet.version = "3.0"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.8
grails.project.source.level = 1.8
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    // compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()

        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        runtime "mysql:mysql-connector-java:5.1.35"
        compile "com.google.guava:guava:18.0"
        compile "com.github.sarxos:webcam-capture:0.3.10"
        compile "net.java.dev.jna:jna:3.5.2"
        compile "com.neurotec:neurotec-core:5.1.0.0"
        compile "com.neurotec:neurotec-licensing:5.1.0.0"
        compile "com.neurotec:neurotec-biometrics:5.1.0.0"
        compile "com.neurotec:neurotec-biometrics-gui:5.1.0.0"
        compile "com.neurotec:neurotec-biometrics-client:5.1.0.0"
        compile "com.neurotec:neurotec-media:5.1.0.0"
        compile "com.neurotec:neurotec-media-processing:5.1.0.0"
        compile "com.neurotec:neurotec-devices:5.1.0.0"
    }

    plugins {
        // plugins for the build system only
        build ":tomcat8:8.0.5"

        // plugins for the compile step
        compile ":cache:1.1.8"
        compile ":asset-pipeline:2.2.3"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate4:4.3.8.1"
    }
}