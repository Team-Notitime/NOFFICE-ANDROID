plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
    alias(libs.plugins.noffice.android.application.firebase)
}

android {
    namespace = "com.easyhz.noffice.data.notification"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.network)

    implementation(projects.core.datastore)
}