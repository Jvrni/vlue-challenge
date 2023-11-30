pluginManagement {
    repositories {
        google()
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

rootProject.name = "Vlue Challenge"
include(":app")
include(":features")
include(":features:home")
include(":features:details")
include(":core")
include(":core:designSystem")
include(":core:service")
include(":core:domain")
include(":core:navigation")
