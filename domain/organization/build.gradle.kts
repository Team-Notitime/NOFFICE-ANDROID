plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.domain.organization"
}

dependencies {
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
}