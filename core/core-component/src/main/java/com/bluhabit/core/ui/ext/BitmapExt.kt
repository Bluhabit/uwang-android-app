/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.ext

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.os.Environment
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.UUID

fun Bitmap.toFile(fileNameToSave: String): File? { // File name like "image.png"
    //create a file to write bitmap data
    var file: File? = null
    val bitmap = this
    return try {
        file = File(Environment.getExternalStorageDirectory().toString() +"/${Environment.DIRECTORY_DCIM}/bluhabit/${fileNameToSave}")
        if(!file.exists()){
            file.mkdir()
            file.createNewFile()
        }


        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
        val bitmapdata = bos.toByteArray()

        //write the bytes in file
        val fos = FileOutputStream(file)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()
        file
    } catch (e: Exception) {
        e.printStackTrace()
        file // it will return null
    }
}

fun Bitmap.toFile(context: Context):File{
    val wrapper = ContextWrapper(context)
    var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
    file = File(file,"${UUID.randomUUID()}.png")
    val stream: OutputStream = FileOutputStream(file)
    this.compress(Bitmap.CompressFormat.PNG,70,stream)
    stream.flush()
    stream.close()
    return file
}