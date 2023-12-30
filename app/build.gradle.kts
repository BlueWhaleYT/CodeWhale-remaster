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
                keyAlias = "keyCodeWhale"
                keyPassword = "M3y7Mk3jNEYthD2s"
                storeFile = file("../codewhale-release.keystore")
                storePassword = "M3y7Mk3jNEYthD2s"
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
    implementation(libs.bundles.test)

    // Modules
    implementation(project(":common"))
    implementation(project(":editor"))

    implementation("com.github.BlueWhaleYT:CrashDebugger:1.0.0.1")

    // Markdown Text - for parsing privacy policy file
    implementation("com.github.jeziellago:compose-markdown:0.3.7")

    // TreeView - for file drawer
    implementation("cafe.adriel.bonsai:bonsai-core:1.2.0")
    implementation("cafe.adriel.bonsai:bonsai-file-system:1.2.0")

    // Coil - for async image and svg file icon from web url
    implementation("io.coil-kt:coil:2.5.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.coil-kt:coil-svg:2.5.0")
}