plugins {
	alias(libs.plugins.android.application) apply false
	alias(libs.plugins.android.library) apply false
	alias(libs.plugins.jetbrains.compose) apply false
	alias(libs.plugins.kotlin.android) apply false
	alias(libs.plugins.kotlin.ksp) apply false
	alias(libs.plugins.kotlin.multiplatform) apply false
	alias(libs.plugins.google.services) apply false
	alias(libs.plugins.firebase.appdistribution) apply false
	alias(libs.plugins.firebase.crashlytics) apply false
	alias(libs.plugins.aboutLibraries) apply false
	alias(libs.plugins.kotest) apply false
	alias(libs.plugins.kover) apply false
	`picover-ktlint` apply true
}

subprojects {
	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
