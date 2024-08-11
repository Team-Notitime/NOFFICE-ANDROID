import java.util.Properties

plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.noffice.android.application.test)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.easyhz.noffice.data.auth"
    buildTypes {
        debug {
            buildConfigField(
                "String",
                "GOOGLE_CLIENT_ID",
                localProperties["google.client.id"].toString()
            )
        }
        release {
//            buildConfigField("String", "GOOGLE_CLIENT_ID", localProperties["google.client.id"].toString())
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.googleid)
    implementation(libs.gms.play.services.auth)
    runtimeOnly(libs.androidx.credentials.play.services.auth)

}