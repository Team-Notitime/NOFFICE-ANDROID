plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
    alias(libs.plugins.noffice.android.application.firebase)
}

android {
    namespace = "com.easyhz.noffice.data.organization"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.model)

    implementation(projects.data.announcement)

    // paging
    implementation(libs.paging.runtime)
}