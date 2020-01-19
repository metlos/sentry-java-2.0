plugins {
    `java-library`
}

dependencies {
    implementation("javax.servlet:javax.servlet-api:3.0.1")
    implementation(project(":sentry-core"))
}
