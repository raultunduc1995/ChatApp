plugins {
    id("core-lib-conventions")
}

android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.3.2"
    packagingOptions.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
}