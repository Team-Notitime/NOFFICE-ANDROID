plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
}

android {
    namespace = "com.easyhz.noffice.core.datastore"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.core)
}