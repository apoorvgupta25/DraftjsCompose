plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
}

android {

    val compileSdkVal = 35
    val minSdkVal = 29

    // Kotlin and JVM configuration
    val jvmTargetVal = "17"
    val kotlinCompilerExtensionVersionVal = "1.5.13"

    namespace = "com.apoorvgupta.draftjscompose"
    compileSdk = compileSdkVal

    defaultConfig {
        minSdk = minSdkVal

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        // Enable Jetpack Compose features
        compose = true
    }

    composeOptions {
        // Set the Kotlin compiler extension version for Jetpack Compose
        kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersionVal
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = jvmTargetVal
    }
}

dependencies {
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.gson)

    implementation(libs.junit)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.github.apoorvgupta"
                artifactId = "draftjs-compose"
                version = "1.1.0"
            }

        }
    }
}
