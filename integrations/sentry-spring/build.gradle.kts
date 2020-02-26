plugins {
    `java-library`
    kotlin("jvm")
}

dependencies {
    compileOnly(Config.CompileOnly.jetbrainsAnnotations)
    implementation(project(":sentry-core"))
    implementation(Config.Libs.springWebMVC)
    implementation(Config.Libs.javaxServlet)
    testImplementation(Config.TestLibs.kotlinTestJunit)
    testImplementation(Config.TestLibs.springTest)
}

configure<SourceSetContainer> {
    test {
        java.srcDir("src/test/java")
    }
}

