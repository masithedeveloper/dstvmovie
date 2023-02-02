import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    jacoco
}

android {
    namespace = "com.example.dstvmovie"
    defaultConfig {
        applicationId = "com.example.dstvmovie"
        buildToolsVersion = "33.0.0"
        minSdk = 23
        compileSdk = 33
        targetSdk = 33
        multiDexEnabled = true
        vectorDrawables {
            useSupportLibrary = true
        }
        versionCode = 1
        versionName = "1.0.0"
        setProperty(
            "archivesBaseName",
            "DSTVMovie_${SimpleDateFormat("yyyyMMdd-HHmm").format(Date())}_v${versionName}(${versionCode})"
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val debugFile = rootProject.file("signing/debug.properties")
    val releaseFile = rootProject.file("signing/release.properties")
    if (releaseFile.exists() && debugFile.exists()) {
        val releaseProperties = Properties()
        releaseProperties.load(FileInputStream(releaseFile))
        val debugProperties = Properties()
        debugProperties.load(FileInputStream(debugFile))
        signingConfigs {
            create("debug-key") {
                storeFile = debugProperties["keystore"]?.let { rootProject.file(it) }
                storePassword = debugProperties["storePassword"]?.toString()
                keyAlias = debugProperties["keyAlias"]?.toString()
                keyPassword = debugProperties["keyPassword"]?.toString()
            }
            create("release-key") {
                storeFile = releaseProperties["keystore"]?.let { rootProject.file(it) }
                storePassword = releaseProperties["storePassword"]?.toString()
                keyAlias = releaseProperties["keyAlias"]?.toString()
                keyPassword = releaseProperties["keyPassword"]?.toString()
            }
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("debug-key")
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release-key")
        }
    }

    flavorDimensions.addAll(listOf("server"))
    productFlavors {
        create("dev") {
            dimension = "server"
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "DSTV Movie AppDev")
        }
        create("prd") {
            dimension = "server"
            resValue("string", "app_name", "DSTV Movie App")
        }
    }

    applicationVariants.all {
        buildConfigField("String", "BASE_URL", "\"https://beta-now.dstv.com\"")
        when (flavorName) {
            "dev" -> {
            }
            "prd" -> {
            }
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = "11"
//        freeCompilerArgs = listOf("-Xallow-result-return-type")
    }
    // https://developer.android.com/topic/libraries/data-binding
    buildFeatures {
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

dependencies {
    // common
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.20")
    implementation("androidx.multidex:multidex:2.0.1")

    // List of KTX extensions
    // https://developer.android.com/kotlin/ktx/extensions-list
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-ktx:1.6.1")
    implementation("androidx.fragment:fragment-ktx:1.5.5")

    // Lifecycle
    // https://developer.android.com/jetpack/androidx/releases/lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.5.1")

    // Preferences DataStore
    // https://android-developers.googleblog.com/2020/09/prefer-storing-data-with-jetpack.html
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // room
    // https://developer.android.com/topic/libraries/architecture/room
    implementation("androidx.room:room-runtime:2.4.3")
    testImplementation("junit:junit:4.12")
    kapt("androidx.room:room-compiler:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")

    // paging
    // https://developer.android.com/topic/libraries/architecture/paging
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    // navigation
    // https://developer.android.com/jetpack/androidx/releases/navigation
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // coroutines
    // https://github.com/Kotlin/kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    // retrofit
    // https://github.com/square/retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")

    // OkHttpProfiler
    // https://github.com/itkacher/OkHttpProfiler
    implementation("com.localebro:okhttpprofiler:1.0.8")

    // glide
    // https://github.com/bumptech/glide
    implementation("com.github.bumptech.glide:glide:4.14.2")
    kapt("com.github.bumptech.glide:compiler:4.14.2")

    // dagger hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // runtime permission
    // https://github.com/googlesamples/easypermissions
    implementation("pub.devrel:easypermissions:3.0.0")

    // lottie
    // https://github.com/airbnb/lottie-android
//    implementation("com.airbnb.android:lottie:3.4.2")

    // timber
    // https://github.com/JakeWharton/timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // viewpager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // compose
    // https://developer.android.com/jetpack/compose/interop/adding
    // https://developer.android.com/jetpack/compose/setup
    val composeBom = platform("androidx.compose:compose-bom:2022.12.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    // Animations
    implementation("androidx.compose.animation:animation")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation")
    // or Material Design 2
    implementation("androidx.compose.material:material")
    // Material Design
    implementation("androidx.compose.material3:material3")
    // Constraint layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
//    implementation("androidx.compose.material:material-icons-core")
    // Optional - Add full set of material icons
    implementation("androidx.compose.material:material-icons-extended")
    // Optional - Add window size utils
    implementation("androidx.compose.material3:material3-window-size-class")
    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.6.1")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    // Optional - Integration with LiveData
    implementation("androidx.compose.runtime:runtime-livedata")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    // https://github.com/skydoves/landscapist
    implementation("com.github.skydoves:landscapist-bom:2.1.0")
    implementation("com.github.skydoves:landscapist-glide")
    implementation("com.github.skydoves:landscapist-placeholder")
    // https://google.github.io/accompanist/
    // https://github.com/google/accompanist
    val accompanistVersion = "0.28.0"
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-permissions:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-placeholder:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-material:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-webview:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-adaptive:$accompanistVersion")

    // unit test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.9.0")
//    testImplementation("org.mockito:mockito-inline:3.3.3")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.2")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.20")
}

kapt {
    correctErrorTypes = true
}

jacoco {
    toolVersion = "0.8.8"
}

project.afterEvaluate {
    // Grab all build types and product flavors
    val buildTypeNames: List<String> = android.buildTypes.map { it.name }
    val productFlavorNames: ArrayList<String> = ArrayList(android.productFlavors.map { it.name })
    // When no product flavors defined, use empty
    if (productFlavorNames.isEmpty()) productFlavorNames.add("")
    productFlavorNames.forEach { productFlavorName ->
        buildTypeNames.forEach { buildTypeName ->
            val sourceName: String
            val sourcePath: String
            if (productFlavorName.isEmpty()) {
                sourcePath = buildTypeName
                sourceName = buildTypeName
            } else {
                sourcePath = "${productFlavorName}/${buildTypeName}"
                sourceName = "${productFlavorName}${buildTypeName.capitalize()}"
            }
            val testTaskName = "test${sourceName.capitalize()}UnitTest"
            // Create coverage task of form 'testFlavorTypeCoverage' depending on 'testFlavorTypeUnitTest'
            task<JacocoReport>("${testTaskName}Coverage") {
                //where store all test to run follow second way above
                group = "coverage"
                description =
                    "Generate Jacoco coverage reports on the ${sourceName.capitalize()} build."
                val excludeFiles = arrayListOf(
                    "**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*",
                    "**/*Test*.*", "android/**/*.*",
                    "**/*_MembersInjector.class",
                    "**/Dagger*Component.class",
                    "**/Dagger*Component\$Builder.class",
                    "**/*_*Factory.class",
                    "**/*ComponentImpl.class",
                    "**/*SubComponentBuilder.class",
                    "**/*Creator.class",
                    "**/*Application*.*",
                    "**/*Activity*.*",
                    "**/*Fragment*.*",
                    "**/*Adapter*.*",
                    "**/*Dialog*.*",
                    "**/*Args*.*",
                    "**/*Companion*.*",
                    "**/*Kt*.*",
                    "**/com/example/dstvmovie/di/**/*.*",
                    "**/com/example/dstvmovie/ui/navigation/**/*.*",
                    "**/com/example/dstvmovie/ui/widgets/**/*.*"
                )
                //Explain to Jacoco where are you .class file java and kotlin
                classDirectories.setFrom(
                    fileTree("${project.buildDir}/intermediates/classes/${sourcePath}").exclude(
                        excludeFiles
                    ),
                    fileTree("${project.buildDir}/tmp/kotlin-classes/${sourceName}").exclude(
                        excludeFiles
                    )
                )
                val coverageSourceDirs = arrayListOf(
                    "src/main/java",
                    "src/$productFlavorName/java",
                    "src/$buildTypeName/java"
                )
                additionalSourceDirs.setFrom(files(coverageSourceDirs))
                //Explain to Jacoco where is your source code
                sourceDirectories.setFrom(files(coverageSourceDirs))
                //execute file .exec to generate data report
                executionData.setFrom(files("${project.buildDir}/jacoco/${testTaskName}.exec"))
                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }
                dependsOn(testTaskName)
            }
        }
    }
}
