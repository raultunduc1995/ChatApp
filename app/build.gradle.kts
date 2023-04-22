plugins {
    id("android-app-conventions")
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
    implementation(project(":core:databases"))
    implementation(project(":data:chat"))
    implementation(project(":feature:chat"))

    // AndroidX
    implementation("androidx.activity:activity-ktx")
    implementation("androidx.core:core-ktx")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx")

    // UI Toolkit
    implementation("androidx.activity:activity-compose")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Dependency injection
    implementation("io.insert-koin:koin-androidx-compose")

    // Navigation
    implementation("androidx.navigation:navigation-compose")

    // Testing
    testImplementation("junit:junit")
    androidTestImplementation("androidx.test.ext:junit")
    androidTestImplementation("androidx.test.espresso:espresso-core")
}