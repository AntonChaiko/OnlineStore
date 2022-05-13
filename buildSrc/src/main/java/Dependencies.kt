object Dependencies {

    object Design {
        const val material = "com.google.android.material:material:1.5.0"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.3"
    }

    object Core {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"

    }

    object Test {
        const val jUnit = "junit:junit:4.13.2"
        const val jUnitExt = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"

    }

    object Hilt {
        const val version = "2.40.5"
        const val android = "com.google.dagger:hilt-android:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0-rc01"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    object Room {
        private const val version = "2.3.0"
        const val ktx = "androidx.room:room-ktx:$version"
        const val runtime = "androidx.room:room-runtime:$version"
        const val paging = "androidx.room:room-paging:2.4.0-alpha04"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object Navigation {
        private const val nav_version = "2.4.1"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
        const val navUi = "androidx.navigation:navigation-ui-ktx:$nav_version"

    }

    object Lifecycle {
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
    }

    object Kotlin {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
    }
}