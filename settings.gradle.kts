pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        includeBuild("gradle/plugins")
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

rootDir.listFiles()
    ?.filter { File(it, "build.gradle.kts").exists() }
    ?.forEach { subproject -> include(subproject.name) }
include(":core:databases")
include(":data:chat")
include("feature:chat")
