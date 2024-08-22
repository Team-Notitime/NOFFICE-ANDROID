import java.util.Properties

plugins {
    alias(libs.plugins.noffice.android.library)
    alias(libs.plugins.noffice.android.library.compose)
    alias(libs.plugins.noffice.android.application.test)
    alias(libs.plugins.noffice.android.hilt)
}


val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.easyhz.noffice.core.common"
    defaultConfig {
        buildConfigField("String", "ENCRYPTION_KEY", localProperties["encryption.key"].toString())
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
}