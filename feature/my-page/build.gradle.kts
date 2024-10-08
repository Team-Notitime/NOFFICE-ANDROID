plugins {
    alias(libs.plugins.noffice.android.library.compose)
    alias(libs.plugins.noffice.android.feature)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}


android {
    namespace = "com.easyhz.noffice.feature.my_page"
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(projects.domain.myPage)
    implementation(projects.domain.organization)
}