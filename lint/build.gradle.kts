plugins {
	kotlin("jvm")
}

dependencies {
	compileOnly(libs.lint.api)
	testImplementation(libs.test.kotest.runner)
	testImplementation(libs.bundles.lint.test)
}
