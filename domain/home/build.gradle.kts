plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.domain.home"
}

dependencies {
    api(projects.core.model)

    implementation(projects.core.common)
    implementation(projects.data.announcement)
    implementation(projects.data.auth)
    implementation(projects.data.member)

    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)

    // paging
    implementation(libs.paging.common)
}