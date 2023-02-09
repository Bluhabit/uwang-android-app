pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(
            url = "https://oss.sonatype.org/content/repositories/snapshots"
        )
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
}

rootProject.name = "BudgetKu"
include(":androidApp")
include(":shared")