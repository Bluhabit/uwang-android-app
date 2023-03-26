/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

plugins {
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("io.gitlab.arturbosch.detekt")
    id("app.cash.sqldelight")
    kotlin("plugin.serialization") version "1.8.0"
    kotlin("kapt")
    kotlin("android")
}

android {
    namespace = "com.bluehabit.budgetku.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(AndroidX.Core.coreKtx)
    with(SQLDelight.App.Cash.Sqldelight){
        api(androidDriver)
    }
    with(Ktor.IO.Ktor){
        api(ktorClientCore)
        api(ktorAndroidClient)
        api(contentNegotioton)
        api(gson)
        api(resource)
    }
    with(Hilt) {
        implementation(hiltNavigationCompose)
        implementation(hiltWork)
        implementation(hiltAndroid)
        kapt(hiltAndroidCompiler)
        kapt(hiltCompiler)
    }

}

sqldelight{
    databases{
        create("Database"){
            packageName.set("com.bluehabit.budgetku.db")
        }
    }
}
kapt {
    correctErrorTypes = true
}