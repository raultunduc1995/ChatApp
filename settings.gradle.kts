pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        includeBuild("gradle/platform")
    }
}

rootProject.name = "Chat App"

include(":app")
include(":core:databases")
include(":data:chat")
include("feature:chat")
