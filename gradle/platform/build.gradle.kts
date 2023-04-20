plugins {
    `java-platform`
}

group = "com.chatapp.platform"

javaPlatform.allowDependencies()

dependencies {
    api(platform("androidx.compose:compose-bom:2022.10.00"))
}
dependencies.constraints {
    // AndroidX
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // UI Toolkit
    api("androidx.activity:activity-compose:1.5.1")
    api("androidx.navigation:navigation-compose:2.5.3")

    // Dependency injection
    api("com.google.dagger:hilt-android:2.44")
    api("com.google.dagger:hilt-android-compiler:2.44")

    // Testing
    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.3")
    api("androidx.test.espresso:espresso-core:3.4.0")
}