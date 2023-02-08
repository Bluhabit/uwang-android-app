object Chucker {
    const val chuckerVersion = "3.5.2"
    val chuckerDebug by lazy{"com.github.chuckerteam.chucker:library:${chuckerVersion}"}
    val chuckerRelease by lazy{"com.github.chuckerteam.chucker:library-no-op:${chuckerVersion}"}
}