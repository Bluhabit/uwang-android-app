/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.com.google.dagger.hilt.android) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.google.services) apply false
    id("com.google.firebase.appdistribution") version "4.0.1" apply false
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        val conf = this
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = listOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            )
        }
    }

}

tasks.getByPath(":androidApp:preBuild").dependsOn("installGitHook")