plugins {
    id("android-lib-conventions")
}

android {
    namespace = "com.example.feature.chat"
}

dependencies {
    implementation(project(":data:chat"))

    // AndroidX
    implementation("androidx.core:core-ktx")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx")

    // UI Toolkit
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