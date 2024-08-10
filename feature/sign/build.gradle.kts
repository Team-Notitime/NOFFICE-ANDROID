plugins {
    alias(libs.plugins.noffice.android.library.compose)
    alias(libs.plugins.noffice.android.feature)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.feature.sign"
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.common)
    implementation(projects.domain.sign)
    implementation(libs.googleid)
    implementation(libs.gms.play.services.auth)
}