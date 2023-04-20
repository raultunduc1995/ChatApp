plugins {
    id("android-app-conventions")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.chatapp"
    defaultConfig {
        applicationId = "com.example.chatapp"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":feature:chat"))

    // AndroidX
    implementation("androidx.core:core-ktx")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx")
    implementation("androidx.activity:activity-compose")

    // UI Toolkit
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Dependency injection
    implementation("com.google.dagger:hilt-android")
    implementation("androidx.core:core-ktx")
    kapt("com.google.dagger:hilt-android-compiler")

    // Navigation
    implementation("androidx.navigation:navigation-compose")

    // Testing
    testImplementation("junit:junit")
    androidTestImplementation("androidx.test.ext:junit")
    androidTestImplementation("androidx.test.espresso:espresso-core")
}