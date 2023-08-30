/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "https://jitpack.io")

    }
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "Eureka Project"
include(":androidApp")

val module = setOf("feature", "data", "core")
module.forEach { subModuleName ->
    File(rootDir, subModuleName)
        .list { file, name -> file.isDirectory && name.startsWith(subModuleName) }
        .forEach { include(":$subModuleName:$it") }
}

rootProject.children.filter { it.name in module }.forEach { module ->
    module.children.forEach { subModule -> project(":${module.name}:${subModule.name}").projectDir = subModule.projectDir }
}