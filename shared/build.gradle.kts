plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.ksp)
	alias(libs.plugins.kotest)
	alias(libs.plugins.kover)
	alias(libs.plugins.jetbrains.compose)
	alias(libs.plugins.aboutLibraries)
}

kotlin {
	jvmToolchain(17)

	androidTarget()

	listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64(),
	).forEach {
		it.binaries.framework {
			baseName = "shared"
			isStatic = true
		}
	}

	sourceSets {
		all {
			languageSettings {
				optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
			}
		}

		commonMain.dependencies {
			implementation(compose.components.resources)
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(compose.materialIconsExtended)
			implementation(compose.ui)
			implementation(libs.compose.material3.window.size)
			implementation(libs.coroutines)
			implementation(libs.firebase.auth)
			implementation(libs.firebase.firestore)
			implementation(libs.firebase.storage)
			implementation(libs.koin.core)
			implementation(libs.koin.compose)
			implementation(libs.voyager.navigator)
			implementation(libs.voyager.tab.navigator)
			implementation(compose.material) // TODO https://github.com/adrielcafe/voyager/issues/185
			implementation(libs.voyager.bottom.sheet.navigator)
			implementation(libs.voyager.koin)
			implementation("co.touchlab:stately-common:2.0.6") {
				because("https://github.com/cashapp/sqldelight/issues/4357#issuecomment-1839905700")
			}
			implementation(libs.coil.compose)
			implementation(libs.coil.network)
			implementation(libs.aboutLibraries)
		}
		commonTest.dependencies {
			implementation(libs.test.coroutines)
			implementation(libs.test.koin)
			implementation(libs.test.kotest.engine)
		}
		androidMain.dependencies {
			implementation(libs.android.compose.activity)
			implementation(libs.android.core)
			implementation(libs.firebaseui.auth)
			implementation("com.google.android.gms:play-services-auth:21.0.0") {
				because("Launching FirebaseUI on Android 14 crashes due to outdated dependency")
			}
			implementation(libs.workmanager)
			implementation(libs.koin.android)
			implementation(libs.koin.workmanager)
			implementation(libs.ktor.engine.android)
			implementation(compose.preview)
			implementation(compose.uiTooling)
		}
		iosMain.dependencies {
			implementation(libs.ktor.engine.ios)
		}
		val androidUnitTest by getting {
			dependencies {
				implementation(libs.test.kotest.datatest)
				implementation(libs.test.kotest.runner)
				implementation(libs.test.mockk)
			}
		}
	}

	@Suppress("OPT_IN_USAGE")
	compilerOptions {
		freeCompilerArgs.add("-Xexpect-actual-classes")
	}
}

android {
	namespace = "com.intive.picover.shared"
	compileSdk = 34

	sourceSets["main"].resources.srcDir("src/commonMain/resources")

	defaultConfig {
		minSdk = 26
	}

	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.11"
	}

	buildFeatures {
		compose = true
	}
}
