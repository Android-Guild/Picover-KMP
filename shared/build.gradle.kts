plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotest)
}

kotlin {
	androidTarget {
		jvmToolchain(17)
	}

	sourceSets {
		commonMain.dependencies {
			implementation(libs.coroutines)
			api(libs.firebase.auth)
			implementation(libs.firebase.firestore)
			implementation(libs.firebase.storage)
		}
		commonTest.dependencies {
			implementation(libs.test.kotlin)
			implementation(libs.test.kotest.engine)
			implementation(libs.test.mockk)
		}
		val androidUnitTest by getting {
			dependencies {
				implementation(libs.test.kotest.runner)
			}
		}
	}
}

android {
	namespace = "com.intive.picover.shared"
	compileSdk = 34
	defaultConfig {
		minSdk = 24
	}
}
