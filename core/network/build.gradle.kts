import java.util.Properties

plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.hilt)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.easyhz.noffice.core.network"
    buildTypes {
        debug {
            buildConfigField("String", "NOFFICE_BASE_URL", localProperties["noffice.base.url.dev"].toString())
        }
        release {
            buildConfigField("String", "NOFFICE_BASE_URL", localProperties["noffice.base.url.prod"].toString())
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}