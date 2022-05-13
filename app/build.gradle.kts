plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.30"
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        dataBinding = true
    }
}

dependencies {

    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.appCompat)

    implementation(Dependencies.Design.material)
    implementation(Dependencies.Design.constraint)

    implementation(Dependencies.Lifecycle.lifecycleKtx)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.0")
    // Navigation
    implementation(Dependencies.Navigation.navFragment)
    implementation(Dependencies.Navigation.navUi)

    //Serialization
    implementation(Dependencies.Kotlin.serialization)

    //Hilt
    implementation(Dependencies.Hilt.android)
    implementation(Dependencies.Hilt.navigation)
    implementation("com.google.firebase:firebase-firestore-ktx:24.0.2")
    implementation("com.google.firebase:firebase-auth-ktx:21.0.2")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.8.1")
    kapt(Dependencies.Hilt.compiler)

    //Round image
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    // Room
    implementation(Dependencies.Room.ktx)
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.paging)
    kapt(Dependencies.Room.compiler)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
}