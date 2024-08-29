plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

android {
    namespace = "com.easyhz.noffice.data.auth"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.model)

    implementation(projects.data.notification)

    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.googleid)
    implementation(libs.gms.play.services.auth)
    runtimeOnly(libs.androidx.credentials.play.services.auth)

}