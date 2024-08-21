plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.domain.sign"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.data.auth)
    implementation(projects.data.notification)
    implementation(libs.googleid)
    implementation(libs.gms.play.services.auth)
}