/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.ext

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

fun Uri.getBitmap(c: ContentResolver): Bitmap?{
    return try {
        val input =  c.openInputStream(this)
        val btmp = BitmapFactory.decodeStream(input)

        Bitmap.createScaledBitmap(
            btmp,
            120,
            120,
            false
        )

    }catch (e:Exception){
        null
    }
}