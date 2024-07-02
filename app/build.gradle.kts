plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.dipractice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dipractice"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)
    kapt(libs.androidx.room.room.compiler)

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")

        val paging_version = "3.3.0"

        implementation(libs.androidx.paging.runtime.ktx)

        // alternatively - without Android dependencies for tests
        testImplementation(libs.androidx.paging.common)

        // optional - RxJava2 support
        implementation(libs.androidx.paging.rxjava2)

        // optional - RxJava3 support
        implementation(libs.androidx.paging.rxjava3)

        // optional - Guava ListenableFuture support
        implementation(libs.androidx.paging.guava)

        // optional - Jetpack Compose integration
        implementation(libs.androidx.paging.compose)

}
kapt {
    correctErrorTypes = true
}