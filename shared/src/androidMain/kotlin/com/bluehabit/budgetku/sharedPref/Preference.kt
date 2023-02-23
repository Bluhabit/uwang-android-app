package com.bluehabit.budgetku.sharedPref

import android.os.Build
import androidx.annotation.RequiresApi

const val NAME = ""

@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
actual fun KMMContext.putInt(key: String, value: Int) {
    getSpEditor().putInt(key, value).apply()
}

actual fun KMMContext.getInt(key: String, default: Int): Int {
    return  getSp().getInt(key, default )
}

@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
actual fun KMMContext.putString(key: String, value: String) {
    getSpEditor().putString(key, value).apply()
}

actual fun KMMContext.getString(key: String): String? {
    return  getSp().getString(key, null)
}

@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
actual fun KMMContext.putBool(key: String, value: Boolean) {
    getSpEditor().putBoolean(key, value).apply()
}

actual fun KMMContext.getBool(key: String, default: Boolean): Boolean {
    return getSp().getBoolean(key, default)
}

private fun KMMContext.getSp() = getSharedPreferences(NAME, 0)

private fun KMMContext.getSpEditor() = getSp().edit()