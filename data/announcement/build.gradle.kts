plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}


android {
    namespace = "com.easyhz.noffice.data.announcement"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(libs.jsoup)
}