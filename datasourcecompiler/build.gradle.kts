plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.andrespelaezp.datasourcecompiler"
    compileSdk = 35

    defaultConfig {
//        applicationId = "com.andrespelaezp.datasourcecompiler"
        minSdk = 24
        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"

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
}

dependencies {


    // Coroutines
    implementation(libs.coroutines)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    implementation(libs.androidx.lifecycle.viewmodel.android)

    //DI
//    implementation(libs.koin)
//    implementation(libs.koin.viewmodel)
//    implementation(libs.koin.compose)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
//    testImplementation(libs.koin.test)
//    testImplementation(libs.koin.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}