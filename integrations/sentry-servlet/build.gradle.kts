plugins {
    `java-library`
}

dependencies {
    implementation(Config.Libs.javaxServlet)
    implementation(project(":sentry-core"))
}
