import com.android.build.api.dsl.LibraryBuildType

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.PARCELIZE)
    kotlin(Plugins.ANDROID)
    kotlin(Plugins.KAPT)
    id(Plugins.DAGGER_HILT)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION

        version = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    sourceSets {
        getByName(Flavors.Default.MAIN) {
            java.srcDir("src/main/kotlin")
        }
    }

    buildTypes {
        getByName(Flavors.BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://gateway.marvel.com:443/")
            stringField(Fields.SERVICE_API_KEY to "781ef9ef74b9ff4d6134ea69cdd8e190")
            stringField(Fields.SERVICE_PRIVATE_KEY to "1fe45ac9c78c3131d770d861099892a4b89e0946")
        }
    }

    buildTypes {
        getByName(Flavors.BuildTypes.DEBUG) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://gateway.marvel.com:443/")
            stringField(Fields.SERVICE_API_KEY to "781ef9ef74b9ff4d6134ea69cdd8e190")
            stringField(Fields.SERVICE_PRIVATE_KEY to "1fe45ac9c78c3131d770d861099892a4b89e0946")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    implementation(libs.bundles.room)
    implementation(libs.hilt.android)
    implementation(libs.bundles.okhttp)
    implementation(libs.sandwich)
    implementation(libs.bundles.moshi)
    implementation(libs.timber)
    implementation(libs.bundles.retrofit)
    kapt(libs.hilt.compiler)
    kapt(libs.room.compiler)

    testImplementation(libs.bundles.unitTest)
    androidTestImplementation(libs.bundles.instrumentationTest)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}
