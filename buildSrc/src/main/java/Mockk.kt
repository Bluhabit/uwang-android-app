object Mockk {
    private val mockVersion = "1.13.4"
    object Io {
        object Mockk {
            val mockk by lazy {"io.mockk:mockk:$mockVersion"}
            val mockkAndroid by lazy {"io.mockk:mockk-android:$mockVersion"}
        }
    }
}