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

include(
    ":core:core-data",
    ":core:core-component"
)
include(
    ":data:data-authentication"
)
include(
    ":feature:feature-authentication",
    ":feature:feature-dashboard"
)
rootProject.children.forEach {
    logger.error(it.name)
    if(it.name in setOf("feature","data","core")){
        it.children.forEach {project->
            project(":${it.name}:${project.name}").projectDir = File("${it.name}/${project.name}")
        }
    }
}