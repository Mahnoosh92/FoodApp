pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FoodApp"
include(":app")
include(":core:common")
include(":core:network")
include(":feature:home")
include(":feature:cart")
include(":feature:profile")
include(":feature:search")
include(":core:database")
include(":core:threading")
include(":core:systemdesign")
include(":feature:onboarding")
include(":feature:splash")
