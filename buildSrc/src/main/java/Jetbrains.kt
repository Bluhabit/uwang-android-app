object Jetbrains {
    object Kotlinx {
        val kotlinxCoroutineAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4" }
        val kotlinxCoroutineCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4" }
        //for use `await()` with google and firebase Task API

        val googlePlayKotlinCoroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${AppConfig.KotlinVersion}" }
        val kotlinxCoroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${AppConfig.KotlinVersion}" }

    }
}