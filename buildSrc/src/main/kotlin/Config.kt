object Libs {

    // Support libs
    const val kotlinVersion = "1.4.10"
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0-alpha1"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
    const val material = "com.google.android.material:material:1.3.0-alpha04"
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val multidex = "androidx.multidex:multidex:2.0.1"

    // List of KTX extensions
    const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha05"
    const val activityKtx = "androidx.activity:activity-ktx:1.2.0-alpha05"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.0-alpha05"

    // lifecycle
    private const val lifecycleVersion = "2.2.0"
    const val lifecycleLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    // alternately - if using Java8, use the following instead of lifecycle-compiler, provide @OnLifecycleEvent
    const val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
    // Saved state module for ViewModel
    const val lifecycleSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion"

    // room
    private const val roomVersion = "2.2.5"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"

    // paging
    const val paging = "androidx.paging:paging-runtime-ktx:3.0.0-alpha07"

    // navigation
    const val navigationVersion = "2.3.1"
    const val navigationRuntimeKtx = "androidx.navigation:navigation-runtime-ktx:$navigationVersion"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    // Dynamic Feature Module Support
    const val navigationDynamicModule =
        "androidx.navigation:navigation-dynamic-features-fragment::$navigationVersion"

    // work
    const val workManager = "androidx.work:work-runtime-ktx:2.3.4"

    // rx
    const val rxjava = "io.reactivex.rxjava3:rxjava:3.0.0"

    // coroutines
    private const val coroutinesVersion = "1.3.9"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"

    // gson
    const val gson = "com.google.code.gson:gson:2.8.6"

    // Moshi
    const val moshiVersion = "1.11.0"
    const val moshi = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    // retrofit
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    const val retrofitRxjava = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    const val okLogging = "com.squareup.okhttp3:logging-interceptor:4.7.2"

    // glide
    private const val glideVersion = "4.11.0"
    const val glideRuntime = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"

    // runtime permission
    const val easyPermissions = "pub.devrel:easypermissions:3.0.0"

    // Timber for logging
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    // lottie
    const val lottie = "com.airbnb.android:lottie:3.4.0"
    
    // koin
    const val koinVersion = "2.1.6"
    const val koinCore = "org.koin:koin-core:$koinVersion"
    const val koinAndroid = "org.koin:koin-android:$koinVersion"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
    const val koinScope = "org.koin:koin-androidx-scope:$koinVersion"

    // dagger hilt
    const val daggerHiltVersion = "2.28-alpha"
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:$daggerHiltVersion"
    const val daggerHiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$daggerHiltVersion"
    const val daggerHiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
    const val daggerHiltViewModelCompiler = "androidx.hilt:hilt-compiler:1.0.0-alpha01"

    // leak canary
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"

    const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"

    const val lombokVersion = "1.18.12"
    const val lombok = "org.projectlombok:lombok:$lombokVersion"
    const val annotationLombok = "org.projectlombok:lombok:$lombokVersion"

    // test
    const val junit = "junit:junit:4.13"
    const val mockitoVersion = "3.6.0"
    const val mockitoCore = "org.mockito:mockito-core:$mockitoVersion"
    const val mockitoAndroid = "org.mockito:mockito-android:$mockitoVersion"
    const val mockkVersion = "1.10.2"
    const val mockkCore = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"
    const val testCore = "androidx.test:core:1.3.0"
    const val archCore = "androidx.arch.core:core-testing:2.1.0"
    const val roomTest = "android.arch.persistence.room:testing:1.1.1"
}
