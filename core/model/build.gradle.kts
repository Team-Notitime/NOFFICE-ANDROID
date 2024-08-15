plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhz.noffice.core.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}