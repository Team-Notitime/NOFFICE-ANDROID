import com.easyhz.noffice.build_logic.convention.NofficeBuildType

plugins {
    alias(libs.plugins.noffice.android.application)
    alias(libs.plugins.noffice.android.application.compose)
    alias(libs.plugins.noffice.android.application.flavors)
    alias(libs.plugins.noffice.android.application.test)
    alias(libs.plugins.noffice.android.application.firebase)
    alias(libs.plugins.noffice.android.hilt)
}

android {
    namespace = "com.easyhz.noffice"

    defaultConfig {
        applicationId = "com.easyhz.noffice"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

}