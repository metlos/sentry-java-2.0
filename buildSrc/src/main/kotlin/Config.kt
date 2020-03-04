object Config {
    const val kotlinVersion = "1.3.61"
    const val kotlinStdLib = "stdlib-jdk8"

    object BuildPlugins {
        const val kotlinGradlePlugin = "gradle-plugin"
    }

    private const val springVersion = "4.3.6.RELEASE"
    private const val springBootVersion = "1.5.1.RELEASE"

    object Libs {

        // only bump gson if https://github.com/google/gson/issues/1597 is fixed
        const val gson = "com.google.code.gson:gson:2.8.5"
        const val javaxServlet = "javax.servlet:javax.servlet-api:3.0.1"
        const val springWebMVC = "org.springframework:spring-webmvc:$springVersion"
        const val springBoot = "org.springframework.boot:spring-boot:$springBootVersion"
        const val springBootAutoConfigure = "org.springframework.boot:spring-boot-autoconfigure:$springBootVersion"
        const val springBootStarterWeb = "org.springframework.boot:spring-boot-starter-web:$springBootVersion"
        const val springBootAutoconfigureProcessor = "org.springframework.boot:spring-boot-autoconfigure-processor:$springBootVersion"
        const val springBootConfigurationProcessor = "org.springframework.boot:spring-boot-configuration-processor:$springBootVersion"
        const val springContext = "org.springframework:spring-context:$springVersion"
    }

    object TestLibs {
        const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        const val springTest = "org.springframework:spring-test:$springVersion"
        const val springBootTest = "org.springframework.boot:spring-boot-starter-test:$springBootVersion"
    }

    object QualityPlugins {
        const val jacocoVersion = "0.8.5"
        const val spotless = "com.diffplug.gradle.spotless"
        const val spotlessVersion = "3.27.0"
        const val errorProne = "net.ltgt.errorprone"
        const val errorpronePlugin = "net.ltgt.gradle:gradle-errorprone-plugin:1.1.1"
        const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.28.0"
        const val gradleVersions = "com.github.ben-manes.versions"
    }

    object Sentry {
        const val SENTRY_CLIENT_NAME = "sentry.java"
        const val group = "io.sentry"
        //        TODO: change version to publish new version
        const val version = "2.1.0-SNAPSHOT"
        const val description = "SDK for sentry.io"
        //        TODO: change version code to publish new version, follow the pattern of `version`
        const val buildVersionCode = 20017
        const val website = "https://sentry.io"
        const val userOrg = "getsentry"
        const val repoName = "sentry-android"
        const val licence = "MIT"
        const val licenceUrl = "http://www.opensource.org/licenses/mit-license.php"
        const val issueTracker = "https://github.com/getsentry/sentry-android/issues"
        const val repository = "https://github.com/getsentry/sentry-android"
        const val devName = "Sentry Team and Contributors"
        const val devEmail = "accounts@sentry.io"
        const val scmConnection = "scm:git:git://github.com/getsentry/sentry-android.git"
        const val scmDevConnection = "scm:git:ssh://github.com:getsentry/sentry-android.git"
        const val scmUrl = "https://github.com/getsentry/sentry-android/tree/master"
    }

    object CompileOnly {
        private const val nopenVersion = "1.0.1"

        const val jetbrainsAnnotations = "org.jetbrains:annotations:19.0.0"
        const val nopen = "com.jakewharton.nopen:nopen-annotations:$nopenVersion"
        const val nopenChecker = "com.jakewharton.nopen:nopen-checker:$nopenVersion"
        const val errorprone = "com.google.errorprone:error_prone_core:2.3.4"
        const val errorProneJavac8 = "com.google.errorprone:javac:9+181-r4173-1"
    }

    object Deploy {
        const val novodaBintrayPlugin = "com.novoda:bintray-release:1.0.1"
        const val novodaBintray = "com.novoda.bintray-release"
        const val sign = true
        const val mavenCentralSync = true
    }
}
