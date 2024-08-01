plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.domain.announcement"
}

dependencies {
    api(projects.core.model)
    implementation(projects.data.announcement)
    implementation(projects.core.common)
}