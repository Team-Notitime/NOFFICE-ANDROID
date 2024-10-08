plugins {
    alias(libs.plugins.noffice.android.library.compose)
    alias(libs.plugins.noffice.android.feature)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.feature.home"
}

dependencies {
    implementation(libs.androidx.activity.ktx)

    implementation(projects.core.designSystem)
    implementation(projects.core.common)
    implementation(projects.domain.home)
    implementation(projects.domain.announcement)
    implementation(projects.domain.notification)
    implementation(projects.domain.myPage)
    implementation(projects.domain.organization)

    // splash
    api(libs.androidx.core.splashscreen)

    // paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
}