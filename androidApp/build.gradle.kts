import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
	alias(libs.plugins.android.application)
	kotlin("android")
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
				serviceCredentialsFile = "androidApp/service-credentials.json"
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
		kotlinCompilerExtensionVersion = "1.5.9"
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
	implementation(libs.android.compose.activity)
	implementation(platform(libs.firebase.bom))
	implementation(libs.firebase.crashlytics)
	debugImplementation(libs.leakcanary)
	testImplementation(libs.test.kotest.runner)
	testImplementation(libs.test.junit.launcher)
}

dependencies {
	kover(project(":shared"))
}
