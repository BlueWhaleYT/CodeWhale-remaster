@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinGradlePlugin)
}

android {
    namespace = "com.bluewhaleyt.codewhale"
    compileSdk = ProjectVersion.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.bluewhaleyt.codewhale"
        minSdk = ProjectVersion.MIN_SDK_VERSION
        targetSdk = ProjectVersion.TARGET_SDK_VERSION
        versionCode = ProjectVersion.VERSION_CODE
        versionName = ProjectVersion.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        signingConfigs {
            create("release") {
                keyAlias = "keyGlobal"
                keyPassword = "rQ7QZPMxZEpDRsvZ"
                storeFile = file("/Users/bluewhaleyt/AndroidStudioProjects/globalkeystore.keystore")
                storePassword = "rQ7QZPMxZEpDRsvZ"
            }
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = ProjectVersion.JAVA_VERSION
        targetCompatibility = ProjectVersion.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectVersion.JVM_TARGET_VERSION
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.core)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.composeAccompanist)

    // Modules
    implementation(project(":common"))
    implementation(project(":editor"))

    // Markdown Text
    implementation("com.github.jeziellago:compose-markdown:0.3.7")
}