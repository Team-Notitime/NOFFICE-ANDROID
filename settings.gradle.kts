pluginManagement {
    includeBuild("build-logic")
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
         maven (url = "https://devrepo.kakao.com/nexus/content/groups/public/" )
    }
}

rootProject.name = "Noffice"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":domain:home")
include(":feature:home")
include(":core:common")
include(":core:design-system")
include(":core:network")
include(":feature:organization")
include(":feature:announcement")
include(":feature:notification")
include(":data:announcement")
include(":feature:my-page")
include(":data:notification")
include(":data:organization")
include(":data:member")
include(":data:auth")
include(":domain:organization")
include(":domain:announcement")
include(":domain:my-page")
include(":domain:notification")
include(":core:model")
include(":feature:sign")
include(":domain:sign")
include(":core:datastore")
