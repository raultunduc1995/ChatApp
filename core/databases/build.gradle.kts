plugins {
    id("core-lib-conventions")
}

android {
    namespace = "com.example.core.databases"
}

dependencies {
    // AndrodiX
    implementation("androidx.core:core-ktx")

    // Dependency injection
    implementation("io.insert-koin:koin-androidx-compose")

    // Room
    implementation("androidx.room:room-runtime")
    kapt("androidx.room:room-compiler")
    implementation("androidx.room:room-ktx")

    // Testing
    testImplementation("junit:junit")
    androidTestImplementation("androidx.test.ext:junit")
    androidTestImplementation("androidx.test.espresso:espresso-core")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}