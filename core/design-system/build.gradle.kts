plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.library.compose)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.core.design_system"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}