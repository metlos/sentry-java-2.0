plugins {
    `java-library`
}

dependencies {
    implementation(Config.Libs.log4j)
    implementation(project(":sentry-core"))
    testImplementation(Config.TestLibs.kotlinTestJunit)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
