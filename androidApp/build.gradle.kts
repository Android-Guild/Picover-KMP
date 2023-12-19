import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
	alias(libs.plugins.android.application)
	kotlin("android")
	alias(libs.plugins.kotlin.ksp)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.google.services)
	id(libs.plugins.oss.licenses.get().pluginId)
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
				serviceCredentialsFile = "app/src/service-credentials.json"
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
	implementation(libs.compose.material.icons)
	implementation(libs.compose.material3.window.size)
	implementation(libs.android.core)
	implementation(libs.android.lifecycle)
	implementation(libs.android.compose.activity)
	implementation(libs.android.compose.navigation)
	implementation(libs.android.google.oss.licenses)
	implementation(platform(libs.firebase.bom))
	implementation(libs.firebase.analytics)
	implementation(libs.firebase.crashlytics)
	implementation(libs.firebase.storage)
	implementation(libs.hilt.android)
	implementation(libs.hilt.navigation)
	implementation(libs.hilt.work)
	implementation(libs.coil)
	implementation(libs.placeholder)
	implementation(libs.material)
	implementation(libs.firebaseui.auth)
	implementation(libs.play.services.auth)
	implementation(libs.voyager.hilt)
	debugImplementation(libs.compose.ui.tooling)
	debugImplementation(libs.leakcanary)
	testImplementation(libs.test.kotest.runner)
	testImplementation(libs.test.guava)
	testImplementation(libs.test.junit.launcher)
	testImplementation(libs.test.kotest.datatest)
	testImplementation(libs.test.mockk)
	testCompileOnly("org.jetbrains.kotlin:kotlin-reflect:1.9.10") {
		because("Needed to locally trigger single kotest test - check new versions of kotlin and kotest plugins to fix this workaround")
	}
}

dependencies {
	kover(project(":shared"))
}
