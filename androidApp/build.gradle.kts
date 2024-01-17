import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
	alias(libs.plugins.android.application)
	kotlin("android")
	alias(libs.plugins.kotlin.ksp)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.google.services)
	alias(libs.plugins.firebase.appdistribution)
	alias(libs.plugins.firebase.crashlytics)
	alias(libs.plugins.kover)
}

android {
	namespace = "com.intive.picover"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.intive.picover.kmp"
		minSdk = 26
		targetSdk = 34
		versionCode = System.getenv("GITHUB_RUN_NUMBER")?.toInt() ?: 1
		versionName = "0.1"
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		getByName("debug") {
			firebaseAppDistribution {
				serviceCredentialsFile = "androidApp/src/service-credentials.json"
			}
		}
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}

	buildFeatures {
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.7"
	}

	kotlin {
		jvmToolchain(17)
	}

	lint {
		sarifReport = true
	}
}

dependencies {
	lintChecks(project(":lint"))
	implementation(project(":shared"))
	ksp(libs.hilt.android.compiler)
	implementation(libs.accompanist.navigation.material)
	implementation(platform(libs.compose.bom))
	implementation(libs.compose.ui.ui)
	implementation(libs.compose.ui.preview)
	implementation(libs.compose.material3)
	implementation(libs.android.core)
	implementation(libs.android.lifecycle)
	implementation(platform(libs.firebase.bom))
	implementation(libs.firebase.analytics)
	implementation(libs.firebase.crashlytics)
	implementation(libs.firebase.storage)
	implementation(libs.hilt.android)
	implementation(libs.hilt.work)
	implementation(libs.material)
	implementation(libs.play.services.auth)
	debugImplementation(libs.compose.ui.tooling)
	debugImplementation(libs.leakcanary)
	testImplementation(libs.test.kotest.runner)
	testImplementation(libs.test.junit.launcher)
	testImplementation(libs.test.mockk)
	testCompileOnly("org.jetbrains.kotlin:kotlin-reflect:1.9.10") {
		because("Needed to locally trigger single kotest test - check new versions of kotlin and kotest plugins to fix this workaround")
	}
}

dependencies {
	kover(project(":shared"))
}
