plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.ksp)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.kotest)
}

kotlin {
	androidTarget {
		jvmToolchain(17)
	}

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
		commonMain.dependencies {
			implementation(libs.coroutines)
			implementation(libs.firebase.auth)
			implementation(libs.firebase.firestore)
			implementation(libs.firebase.storage)
		}
		commonTest.dependencies {
			implementation(libs.test.kotlin)
			implementation(libs.test.kotest.engine)
			implementation(libs.test.mockk)
		}
		androidMain.dependencies {
			implementation(libs.android.core)
			implementation(libs.hilt.android)
			implementation(libs.hilt.work)
			implementation(libs.workmanager)
		}
		val androidUnitTest by getting {
			dependencies {
				implementation(libs.test.kotest.runner)
			}
		}
	}
}

dependencies {
	add("kspAndroid", libs.hilt.android.compiler)
}

android {
	namespace = "com.intive.picover.shared"
	compileSdk = 34
	defaultConfig {
		minSdk = 26
	}
}
