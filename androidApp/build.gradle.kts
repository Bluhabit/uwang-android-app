@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.ApkSigningConfig
import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import java.text.SimpleDateFormat
import java.util.Date

/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.app.cash.sqldelight)
    alias(libs.plugins.io.gitlab.arthubosch.detekt)
    id("kotlin-parcelize")
    id("com.google.firebase.appdistribution")
    alias(libs.plugins.google.services)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

android {
    namespace = "${libs.versions.namespace.get()}.android"
    compileSdk = 34
    defaultConfig {
        applicationId = libs.versions.application.id.get()
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        multiDexEnabled = true
        testInstrumentationRunner = "com.bluehabit.budgetku.android.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        create("release") {
            firebaseAppDistribution {
                artifactType = "APK"
                releaseNotesFile = "./FirebaseDistributionConfig/release_notes.txt"
                testers="triandamai@gmail.com"
                serviceCredentialsFile = "./secret/uwang-app-distribution.json"
                appId = "1:616208190167:android:21914953259000a938fe92"
            }
            setupKeystore()
        }
        getByName("debug") {
            setupKeystore()
        }
        getByName("debug") {
            setupKeystore()
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    setFlavorDimensions(listOf("Environment"))
    productFlavors {
        create("dev") {
            dimension = "Environment"
            versionName = "1.0.${getTimestamp()}-dev"
        }
        create("staging") {
            dimension = "Environment"
            versionName = "1.0.${getTimestamp()}-beta"
        }
        create("production") {
            dimension = "Environment"
            versionName = "1.0.${getTimestamp()}"
        }
    }
    applicationVariants.all {
        setProperty("archivesBaseName", "UWANG-1.0.${getTimestamp()}-SNAPSHOT")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    lint {
        baseline = file("lint-baseline.xml")
        abortOnError = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {

    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-analytics")
    api(project(":core:core-component"))
    api(project(":core:core-data"))

    coreLibraryDesugaring(libs.desugar.jdk.lib)
    implementation(libs.multidex)
    implementation(libs.core.ktx)

    with(libs.hilt) {
        implementation(navigation.compose)
        implementation(android)
        implementation(work)
        androidTestImplementation(android.test)
        kapt(android.compiler)
        kaptTest(android.compiler)
        kapt(compiler)
    }

    implementation(libs.work.runtime)

    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    testImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.robolectric)

    debugImplementation(libs.leak.canary)


}
kapt {
    correctErrorTypes = true
}

tasks.create<Copy>("installGitHook") {
    var suffix = "macos"
    if (org.apache.tools.ant.taskdefs.condition.Os.isFamily(org.apache.tools.ant.taskdefs.condition.Os.FAMILY_WINDOWS)) {
        suffix = "windows"
    }

    copy {
        from(File(rootProject.rootDir, "scripts/pre-push-$suffix"))
        into { File(rootProject.rootDir, ".git/hooks") }
        rename("pre-push-$suffix", "pre-push")
    }
    copy {
        from(File(rootProject.rootDir, "scripts/pre-commit-$suffix"))
        into { File(rootProject.rootDir, ".git/hooks") }
        rename("pre-commit-$suffix", "pre-commit")
    }
    fileMode = "775".toInt(8)
}


fun ApkSigningConfig.setupKeystore() {
    keyAlias = findProperty("KEY_ALIAS").toString()
    keyPassword = findProperty("KEY_PASSWORD").toString()
    storeFile = file(findProperty("STORE_PATH").toString())
    storePassword = findProperty("STORE_PASSWORD").toString()
}

fun getTimestamp(): String {
    val formatter = SimpleDateFormat()
    formatter.applyPattern("yyyyMMddHHmmss")
    return formatter.format(Date())
}