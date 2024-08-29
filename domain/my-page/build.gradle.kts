plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.domain.my_page"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.data.auth)
    implementation(projects.data.member)
    implementation(projects.data.notification)
    implementation(projects.data.organization)

    implementation(projects.domain.organization)
}