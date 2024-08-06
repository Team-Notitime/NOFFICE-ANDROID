plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.library.compose)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.core.design_system"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.androidx.core.ktx)

    // Glide
    implementation(libs.glide)

    // Calendar
    implementation(libs.calendar.compose)
    implementation(libs.androidx.webkit)
}