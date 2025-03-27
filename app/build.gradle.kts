plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.library")
}

version = "1.0.0"

android {
    namespace = "com.example.deviceutils"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

// API Log
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

// Retrofit - API
    implementation ("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit sürümünü 2.9.0 olarak ayarladım.
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // Aynı şekilde converter-gson sürümü
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0") // Aynı şekilde rxjava2 adapter sürümü


}
