rootProject.name = "sentry-all"
rootProject.buildFileName = "build.gradle.kts"

include("sentry-core", ":integrations:sentry-servlet", ":integrations:sentry-spring",
        ":integrations:sentry-spring-boot")
