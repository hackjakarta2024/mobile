plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.devToolsKsp)
}

android {
    namespace = "com.ionify.grabbites"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ionify.grabbites"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ui
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.fragment.ktx)

    // glide
    implementation(libs.glide)
    ksp("com.github.bumptech.glide:ksp:4.16.0")


    // network-retrofit
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.converter.gson)

    // network-okhttp3
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.squareup.okhttp3.okhttp)

    // data store
    implementation(libs.androidx.datastore.datastore.preferences)

    // tbuonomo dots indicator
    implementation(libs.tbuonomo.dotsindicator)
}

ksp {
    arg("glide.module", "com.ionify.grabbites") // Sesuaikan dengan nama package dan class GlideModule lo
}