plugins {
    `java-library`
    kotlin("jvm")
}

dependencies {
    compileOnly(Config.CompileOnly.jetbrainsAnnotations)
    implementation(Config.Libs.log4j)
    implementation(project(":sentry-core"))
    testImplementation(Config.TestLibs.kotlinTestJunit)
    testImplementation(Config.TestLibs.mockitoKotlin)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
