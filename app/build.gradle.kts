plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.mvvmormviwithhilt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvvmormviwithhilt"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding=true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.dagger:hilt-android:2.50")
//    kapt("com.google.dagger:hilt-android-compiler:2.50")
    ksp("com.google.dagger:hilt-android-compiler:2.50")

    val hilt_viewmodels = "1.0.0-alpha03"
//    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:$hilt_viewmodels")
//    kapt ("androidx.hilt:hilt-compiler:$hilt_viewmodels")
//    ksp ("androidx.hilt:hilt-compiler:$hilt_viewmodels")

    val retrofit="2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")


    //room
    val room = "2.6.1"
    implementation ("androidx.room:room-runtime:$room")
    implementation( "androidx.room:room-ktx:$room")
//    kapt ("androidx.room:room-compiler:$room")
    ksp("androidx.room:room-compiler:2.6.1")


    val fragment_ktx = "1.6.2"
    implementation ("androidx.fragment:fragment-ktx:$fragment_ktx")

    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

}
// Allow references to generated code
/*
kapt {
    correctErrorTypes = true
}*/
