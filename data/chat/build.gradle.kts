plugins {
    id("core-lib-conventions")
}

android {
    namespace = "com.example.mylibrary"
}

dependencies {
    implementation(project(":core:databases"))

    // AndroidX
    implementation("androidx.core:core-ktx")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")

    // Dependency injection
    implementation("io.insert-koin:koin-androidx-compose")

    // Testing
    testImplementation("junit:junit")
    androidTestImplementation("androidx.test.ext:junit")
    androidTestImplementation("androidx.test.espresso:espresso-core")
}