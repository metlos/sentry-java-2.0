import aQute.bnd.gradle.Bundle

plugins {
    id("biz.aQute.bnd.builder") version "4.3.1"
}

val springVersion = "4.3.10.RELEASE"
val javaxServletVersion = "3.0.1"

dependencies {
    implementation(project(":sentry-core"))
    implementation("org.springframework:spring-webmvc:$springVersion")
    implementation("javax.servlet:javax.servlet-api:$javaxServletVersion")
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

// for love or money, I can't figure this one out... Gradle is too magic for me still
//tasks {
//    val bundle by registering(Bundle::class)
//
//    bundle {
//
//    }
////    bundle {
////        bnd ('Bundle-Name': 'overwrittenSpecialOsgiName',
////             'Bundle-Vendor': 'Sentry',
////             'Bundle-Description': 'Platform2: Metrics 2 Measures Framework',
////             'Bundle-DocURL': 'https://www.mycompany.com')
////    }
//}
