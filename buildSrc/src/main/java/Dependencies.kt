import Versions.archVersion
import Versions.daggerVersion
import Versions.glideVersion
import Versions.kotlinVersion
import Versions.moshiVersion
import Versions.navVersion
import Versions.okHttpVersion
import Versions.retrofitVersion
import Versions.rxVersion
import Versions.supportVersion

object Versions {
    const val supportVersion = "1.0.0-beta01"
    const val kotlinVersion = "1.3.21"
    const val daggerVersion = "2.17"
    const val glideVersion = "4.9.0"
    const val retrofitVersion = "2.4.0"
    const val okHttpVersion = "3.11.0"
    const val moshiVersion = "1.6.0"
    const val rxVersion = "2.2.0"
    const val archVersion = "2.0.0"
    const val navVersion = "2.0.0"
}

object Deps {
    // support
    const val recyclerView = "androidx.recyclerview:recyclerview:$supportVersion"
    const val cardView = "androidx.cardview:cardview:$supportVersion"
    const val material = "com.google.android.material:material:$supportVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    // kotlin
    const val kotlinLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    // dagger
    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
    // glide
    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
    // retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    // okHttp
    const val okhttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    // moshi
    const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    // reactivex
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.0.2"
    const val rxJava = "io.reactivex.rxjava2:rxjava:$rxVersion"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:$rxVersion"
    // tools
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    // architecture component
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$archVersion"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$archVersion"
    const val lifecycleReactivestreams = "androidx.lifecycle:lifecycle-reactivestreams:$archVersion"
    // navigation
    const val navigationFragment =  "androidx.navigation:navigation-fragment-ktx:$navVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navVersion"
    // room
    const val roomRuntime = "androidx.room:room-runtime:$archVersion"
    const val roomCompiler = "androidx.room:room-compiler:$archVersion"
    const val roomRx = "androidx.room:room-rxjava2:$archVersion"
    // testing
    const val junit = "junit:junit:4.12"
    const val testRunner = "androidx.test:runner:1.1.0"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.1.0"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
    const val mockitoInline = "org.mockito:mockito-inline:2.13.0"
    const val coreTesting = "androidx.arch.core:core-testing:2.0.0"
}

object Config {
    const val compileVersion = 28
    const val minVersion = 15
    const val targetVersion = 28
    const val versionCode = 1
    const val versionName = "1.0"
    const val buildTools = "28.0.3"
    const val packageName = "id.arieridwan.mvww"
    const val gradlePlugin = "com.android.tools.build:gradle:3.3.2"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}