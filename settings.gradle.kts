pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        //maven(url="https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
dependencyResolutionManagement {
    //repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        //maven(url="https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "MaterializedSmite"
include(":app")
include(":presentation")
include(":shared")
