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
	":app",
	":shared",
	":lint",
	":ktlint-ruleset",
)
