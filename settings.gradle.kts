pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}
dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
	}
}
rootProject.name = "Picover"
include(
	":androidApp",
	":shared",
	":lint",
	":ktlint-ruleset",
)
