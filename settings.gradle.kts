import org.gradle.api.internal.initialization.ClassLoaderIds.buildScript

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:7.1.2")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.5.31")
            }
            if (requested.id.id.startsWith("dagger.hilt.android")) {
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
            }
            if(requested.id.id.startsWith("com.google.gms")){
                useModule("com.google.gms:google-services:4.3.10")
            }
        }
    }
}

rootProject.name = "OnlineStoreApp"
include (":app")
