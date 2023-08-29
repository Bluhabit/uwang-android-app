/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

@file:Suppress("UnstableApiUsage")

include(":data:data-task")

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

include(
    ":core:core-data",
    ":core:core-component"
)
include(
    ":data:data-authentication",
    ":data:data-task"
)
include(
    ":feature:feature-authentication",
    ":feature:feature-dashboard"
)
rootProject.children.forEach {
    if(it.name in setOf("feature","data","core")){
        val dir = it.name
        it.children.forEach {project->
            val module=project.name
            project(":$dir:$module").projectDir = project.projectDir
        }
    }
}