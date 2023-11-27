plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.ksp)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.kotest)
	alias(libs.plugins.kover)
	alias(libs.plugins.resources)
	alias(libs.plugins.cocoapods)
}

kotlin {
	jvmToolchain(17)

	androidTarget()
	iosX64()
	iosArm64()
	iosSimulatorArm64()

	cocoapods {
		version = "0.1"
		ios.deploymentTarget = "16.0"
		framework {
			baseName = "shared"
			isStatic = true
		}
		pod("FirebaseAuth")
		pod("FirebaseFirestore") {
			extraOpts += listOf("-compiler-option", "-fmodules")
		}
		pod("FirebaseStorage") {
			extraOpts += listOf("-compiler-option", "-fmodules")
		}
	}

	sourceSets {
		commonMain.dependencies {
			implementation(libs.coroutines)
			implementation(libs.firebase.auth)
			implementation(libs.firebase.firestore)
			implementation(libs.firebase.storage)
			implementation(libs.resources)
		}
		commonTest.dependencies {
			implementation(libs.test.kotlin)
			implementation(libs.test.kotest.engine)
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
				implementation(libs.test.mockk)
			}
		}
	}
}

dependencies {
	add("kspAndroid", libs.hilt.android.compiler)
	add("kspAndroid", libs.hilt.compiler)
}

multiplatformResources {
	multiplatformResourcesPackage = "com.intive.picover.shared"
}

android {
	namespace = "com.intive.picover.shared"
	compileSdk = 34

	defaultConfig {
		minSdk = 26
	}

	sourceSets {
		// https://github.com/icerockdev/moko-resources/issues/531
		getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
	}
}
