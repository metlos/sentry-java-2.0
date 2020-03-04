import aQute.bnd.gradle.Bundle

plugins {
    id("biz.aQute.bnd.builder") version "4.3.1"
}

dependencies {
    implementation(project(":sentry-core"))
    implementation(project(":integrations:sentry-servlet"))
    implementation(project(":integrations:sentry-spring"))
    implementation(Config.Libs.springContext)
    implementation(Config.Libs.springBoot)
    implementation(Config.Libs.springBootAutoConfigure)
    implementation(Config.Libs.springBootStarterWeb)
    implementation(Config.Libs.springWebMVC)
    compileOnly(Config.Libs.javaxServlet)
    compileOnly(Config.Libs.springBootConfigurationProcessor)
    compileOnly(Config.Libs.springBootAutoconfigureProcessor)
    compileOnly(Config.CompileOnly.jetbrainsAnnotations)
    testImplementation(Config.TestLibs.kotlinTestJunit)
    testImplementation(Config.TestLibs.springBootTest)
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
