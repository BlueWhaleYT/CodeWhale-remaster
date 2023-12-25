@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinGradlePlugin)
}

android {
    namespace = "com.bluewhaleyt.codewhale.common"
    compileSdk = ProjectVersion.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = ProjectVersion.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
}

dependencies {
    implementation(libs.bundles.core)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.composeAccompanist)
}