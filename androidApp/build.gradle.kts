@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationBuildType
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin.Companion.isIncludeCompileClasspath

/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.app.cash.sqldelight)
    alias(libs.plugins.io.gitlab.arthubosch.detekt)
    id("kotlin-parcelize")
}

android {
    namespace = libs.versions.namespace.get()
    compileSdk = 33
    defaultConfig {
        applicationId = libs.versions.application.id.get()
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.bluehabit.budgetku.android.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
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

    buildTypes {
        release {
            setupBaseUrl()
            setupDatabase()
            setupSharedPrefName()
            isMinifyEnabled = false
        }

        debug {
            setupBaseUrl()
            setupDatabase()
            setupSharedPrefName()
            isDebuggable = true
        }
    }

    signingConfigs {
        create("release") {
            setupKeystore()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi"
        )
    }
}

dependencies {
    implementation(project(":data"))

    coreLibraryDesugaring(libs.desugar.jdk.lib)

    implementation(libs.android.material)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.calendar)
    implementation(libs.wheel.picker.compose)
    implementation(libs.coil.compose)
    implementation(libs.navigation.compose)
    implementation(libs.multidex)

    with(libs.accompanist) {
        implementation(pager)
        implementation(pager.indicator)
        implementation(flow.layout)
        implementation(shimmer)
    }

    with(libs.hilt) {
        implementation(navigation.compose)
        implementation(android)
        implementation(work)
        androidTestImplementation(android.test)
        kapt(android.compiler)
        kaptTest(android.compiler)
        kapt(compiler)
    }
    with(libs.gms.play.service) {
        implementation(auth)
        implementation(base)
    }
    implementation(libs.work.runtime)

    with(libs.kotlinx.coroutine) {
        implementation(android)
        implementation(core)
        implementation(play.services)
        testImplementation(test)
    }

    with(libs.chuker){
        debugApi(debug)
        releaseApi(release)
    }

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

fun ApplicationBuildType.setupBaseUrl() {
    buildConfigField(
        "String",
        "BASE_URL",
        "\"${findProperty("BASE_URL").toString()}\""
    )
}

fun ApplicationBuildType.setupDatabase() {
    buildConfigField(
        "String",
        "DATABASE",
        "\"${findProperty("DATABASE").toString()}\""
    )
}

fun ApplicationBuildType.setupSharedPrefName() {
    buildConfigField(
        "String",
        "SHARED_PREFERENCES",
        "\"${findProperty("SHARED_PREFERENCES").toString()}\""
    )
}

fun ApkSigningConfig.setupKeystore() {
    keyAlias = findProperty("KEY_ALIAS").toString()
    keyPassword = findProperty("KEY_PASSWORD").toString()
    storeFile = file(findProperty("STORE_PATH").toString())
    storePassword = findProperty("STORE_PASSWORD").toString()
}