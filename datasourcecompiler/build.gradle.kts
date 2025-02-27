plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("androidx.room")
}

android {
    namespace = "com.andrespelaezp.datasourcecompiler"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
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
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    // Coroutines
    implementation(libs.coroutines)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)

    implementation(libs.androidx.lifecycle.viewmodel.android)

    //DI
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)


    // Room components
    implementation(libs.androidx.room.runtime)
    implementation(libs.koin.android)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    // WorkManager for background sync
    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.kotlinx.datetime)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.robolectric)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)

    // Coroutines Testing
    testImplementation(libs.kotlinx.coroutines.test)

    // MockWebServer for API testing
    testImplementation(libs.mockwebserver)
}