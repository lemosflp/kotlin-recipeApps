plugins {
    alias(libs.plugins.android.application) // Usa o alias definido no toml
    alias(libs.plugins.kotlin.android) // Usa o alias do Kotlin
    id("com.google.gms.google-services") // Plugin extra para Firebase

}


android {
    namespace = "com.example.apprecipesc"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.apprecipesc"
        minSdk = 24
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


    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
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


    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.firebase:firebase-database:20.2.0")
    implementation ("com.google.firebase:firebase-storage:20.2.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
}
