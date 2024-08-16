import com.easyhz.noffice.build_logic.convention.NofficeBuildType
import java.util.Properties

plugins {
    alias(libs.plugins.noffice.android.application)
    alias(libs.plugins.noffice.android.application.compose)
    alias(libs.plugins.noffice.android.application.flavors)
    alias(libs.plugins.noffice.android.application.test)
    alias(libs.plugins.noffice.android.application.firebase)
    alias(libs.plugins.noffice.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

val keystoreProperties = Properties()
keystoreProperties.load(project.rootProject.file("keystore.properties").inputStream())

android {
    namespace = "com.easyhz.noffice"

    defaultConfig {
        applicationId = "com.easyhz.noffice"
        versionCode = 5
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }
    buildTypes {
        debug {
            applicationIdSuffix = NofficeBuildType.DEBUG.applicationSuffix
        }
        release {
            isMinifyEnabled = false
            applicationIdSuffix = NofficeBuildType.RELEASE.applicationSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designSystem)
    implementation(projects.core.network)
    implementation(projects.core.model)

    implementation(projects.data.auth)
    implementation(projects.data.organization)

    implementation(projects.domain.home)
    implementation(projects.domain.sign)

    implementation(projects.feature.announcement)
    implementation(projects.feature.home)
    implementation(projects.feature.myPage)
    implementation(projects.feature.organization)
    implementation(projects.feature.sign)

    implementation(libs.kotlinx.serialization.json)
}