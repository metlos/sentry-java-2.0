plugins {
    `java-library`
}

dependencies {
    compileOnly(Config.Libs.javaxServlet)
    implementation(project(":sentry-core"))
}
