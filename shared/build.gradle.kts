plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.ksp)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.kotest)
	alias(libs.plugins.kover)
	alias(libs.plugins.resources)
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
		commonMain.dependencies {
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(compose.materialIconsExtended)
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
			implementation("co.touchlab:stately-common:2.0.6") {
				because("https://github.com/cashapp/sqldelight/issues/4357#issuecomment-1839905700")
			}
			implementation(libs.coil.compose)
			implementation(libs.coil.network)
			implementation(libs.aboutLibraries)
		}
		commonTest.dependencies {
			implementation(libs.test.koin)
			implementation(libs.test.kotest.engine)
		}
		androidMain.dependencies {
			implementation(libs.android.compose.activity)
			implementation(libs.android.core)
			implementation(libs.hilt.android)
			implementation(libs.hilt.work)
			implementation(libs.workmanager)
			implementation(libs.koin.android)
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

	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.7"
	}

	buildFeatures {
		compose = true
	}

	sourceSets {
		// https://github.com/icerockdev/moko-resources/issues/531
		getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
	}
}
