plugins {
    alias(libs.plugins.noffice.android.library.compose)
    alias(libs.plugins.noffice.android.feature)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}
android {
    namespace = "com.easyhz.noffice.feature.announcement"
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.common)
    implementation(projects.domain.announcement)
    implementation(projects.domain.myPage)
    implementation(projects.domain.organization)

    // paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
}