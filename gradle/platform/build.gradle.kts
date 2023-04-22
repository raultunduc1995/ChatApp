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
    api("androidx.activity:activity-ktx:1.6.1")
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    // UI Toolkit
    api("androidx.activity:activity-compose:1.6.1")
    api("androidx.navigation:navigation-compose:2.5.3")

    // Dependency injection
    api("io.insert-koin:koin-androidx-compose:3.4.0")

    // Room
    val room_version = "2.5.1"
    api("androidx.room:room-runtime:$room_version")
    api("androidx.room:room-compiler:$room_version")
    api("androidx.room:room-ktx:$room_version")

    // Testing
    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.3")
    api("androidx.test.espresso:espresso-core:3.4.0")
}