import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.ksp)
	alias(libs.plugins.kotest)
	alias(libs.plugins.kover)
	alias(libs.plugins.resources)
	alias(libs.plugins.jetbrains.compose)
	alias(libs.plugins.aboutLibraries)
}

kotlin {
	jvmToolchain(17)

	androidTarget()

	jvm("desktop")

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
			implementation(libs.compose.material3.window.size)
			implementation(libs.coroutines)
			implementation(libs.firebase.auth)
			implementation(libs.firebase.firestore)
			implementation(libs.firebase.storage)
			implementation(libs.resources)
			implementation(libs.resources.compose)
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
			implementation(libs.test.koin)
			implementation(libs.test.kotest.engine)
		}
		androidMain.dependencies {
			implementation(libs.android.compose.activity)
			implementation(libs.android.core)
			implementation(libs.firebaseui.auth)
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
		val desktopMain by getting {
			dependencies {
				implementation(compose.desktop.currentOs)
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.3")
			}
			dependsOn(commonMain.get())
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

multiplatformResources {
	multiplatformResourcesPackage = "com.intive.picover.shared"
	// multiplatformResourcesVisibility = MRVisibility.Internal
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

compose.desktop {
	application {
		mainClass = "MainKt"

		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "com.intive.picover"
			packageVersion = "1.0.0"
		}
	}
}
