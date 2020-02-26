rootProject.name = "sentry-all"
include("buildSrc", "sentry-core", ":integrations:sentry-servlet", ":integrations:sentry-spring",
        ":integrations:sentry-spring-boot")
include("integrations:sentry-spring")
findProject(":integrations:sentry-spring")?.name = "sentry-spring"
