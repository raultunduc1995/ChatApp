plugins {
    id("core-lib-conventions")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.core.databases"
}

dependencies {
    // AndrodiX
    implementation("androidx.core:core-ktx")

    // Dependency injection
    implementation("com.google.dagger:hilt-android")
    implementation("androidx.core:core-ktx")
    kapt("com.google.dagger:hilt-android-compiler")

    // Testing
    testImplementation("junit:junit")
    androidTestImplementation("androidx.test.ext:junit")
    androidTestImplementation("androidx.test.espresso:espresso-core")
}