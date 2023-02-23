plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("io.gitlab.arturbosch.detekt")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.bluehabit.budgetku"
    compileSdk = 33
    defaultConfig {
        applicationId =
            "com.bluehabit.budgetku"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility =
            JavaVersion.VERSION_1_8
        targetCompatibility =
            JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(AndroidX.Core.coreKtx)
    implementation(AndroidX.Lifecycle.runtimeLifecycleKtx)
    implementation(AndroidX.Activity.activityCompose)
    implementation(AndroidX.Multidex.multidex)
    implementation(AndroidX.Navigation.navigationCompose)

    with(JetpackCompose) {
        implementation(platform(composeBom))
        androidTestImplementation(
            platform(
                composeBom
            )
        )
        implementation(material3)
        implementation(ui)
        implementation(uiToolingPreview)
        debugImplementation(uiTooling)
        androidTestImplementation(uiTestJunit4)
        debugImplementation(uiTestManifest)
        implementation(materialIconExtended)
        implementation(materialWindowSizeClass)
    }
    with(Accompanist) {
        implementation(pager)
        implementation(pagerIndicator)
        implementation(flowLayout)
    }
    with(Hilt) {
        implementation(hiltNavigationCompose)
        implementation(hiltWork)
        implementation(hiltAndroid)
        kapt(hiltAndroidCompiler)
        kapt(hiltCompiler)
    }

    with(Worker) {
        implementation(workRuntime)
    }
    implementation(Jetbrains.Kotlinx.kotlinxCoroutineAndroid)

    with(Chucker) {
        debugImplementation(chuckerDebug)
        releaseImplementation(chuckerRelease)
    }
    debugImplementation(LeakCanary.leakCanary)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
//https://dev.to/akdevcraft/git-pre-hook-setup-pre-push-hook-for-gradle-project-example-1nn6
//https://emmanuelkehinde.io/setting-up-git-pre-commit-pre-push-hook-for-ktlint-check/
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

    //make file executable
    fileMode = "775".toInt(8)
}