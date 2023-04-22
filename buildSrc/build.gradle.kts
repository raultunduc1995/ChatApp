plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
}

dependencies {
    implementation("com.android.application:com.android.application.gradle.plugin:8.0.0")
    implementation("com.android.library:com.android.library.gradle.plugin:8.0.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
}