plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.ksp)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.kotest)
	alias(libs.plugins.kover)
	alias(libs.plugins.resources)
	alias(libs.plugins.jetbrains.compose)
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
		commonMain.dependencies {
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(compose.ui)
			implementation(libs.coroutines)
			implementation(libs.firebase.auth)
			implementation(libs.firebase.firestore)
			implementation(libs.firebase.storage)
			implementation(libs.resources)
			api(libs.resources.compose)
			implementation(libs.koin.core)
			api(libs.voyager.navigator)
			api(libs.voyager.tab.navigator)
			implementation(compose.material) // TODO https://github.com/adrielcafe/voyager/issues/185
			api(libs.voyager.bottom.sheet.navigator)
			api(libs.voyager.koin)
		}
		commonTest.dependencies {
			implementation(libs.test.koin)
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
