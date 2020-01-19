object Config {
    val kotlinVersion = "1.3.61"
    val kotlinStdLib = "stdlib-jdk8"

    object BuildPlugins {
        val kotlinGradlePlugin = "gradle-plugin"
    }

    object Libs {
        // only bump gson if https://github.com/google/gson/issues/1597 is fixed
        val gson = "com.google.code.gson:gson:2.8.5"
    }

    object TestLibs {
        val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
        val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
    }

    object QualityPlugins {
        val jacocoVersion = "0.8.5"
        val spotless = "com.diffplug.gradle.spotless"
        val spotlessVersion = "3.25.0"
        val errorProne = "net.ltgt.errorprone"
        val errorpronePlugin = "net.ltgt.gradle:gradle-errorprone-plugin:1.1.1"
        val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.27.0"
        val gradleVersions = "com.github.ben-manes.versions"
    }

    object Sentry {
        val SENTRY_CLIENT_NAME = "sentry.java.android"
        val group = "io.sentry"
        //        TODO: change version to publish new version
        val version = "2.0.0-beta02"
        val description = "SDK for sentry.io"
        //        TODO: change version code to publish new version, follow the pattern of `version`
        val buildVersionCode = 20011
        val website = "https://sentry.io"
        val userOrg = "getsentry"
        val repoName = "sentry-android"
        val licence = "MIT"
        val issueTracker = "https://github.com/getsentry/sentry-android/issues"
        val repository = "https://github.com/getsentry/sentry-android"
        val devName = "Sentry Team and Contributors"
        val devEmail = "accounts@sentry.io"
        val devUser = "getsentry"
    }

    object CompileOnly {
        private val nopenVersion = "1.0.1"

        val jetbrainsAnnotations = "org.jetbrains:annotations:18.0.0"
        val nopen = "com.jakewharton.nopen:nopen-annotations:$nopenVersion"
        val nopenChecker = "com.jakewharton.nopen:nopen-checker:$nopenVersion"
        val errorprone = "com.google.errorprone:error_prone_core:2.3.4"
        val errorProneJavac8 = "com.google.errorprone:javac:9+181-r4173-1"
    }

    object Deploy {
        val novodaBintrayPlugin = "com.novoda:bintray-release:0.9.2"
        val novodaBintray = "com.novoda.bintray-release"
        val sign = true
    }
}
