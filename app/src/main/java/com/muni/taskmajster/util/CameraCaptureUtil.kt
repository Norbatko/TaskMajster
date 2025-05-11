package com.muni.taskmajster.util

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import com.muni.taskmajster.model.data.ImageUriResult
import java.text.SimpleDateFormat
import java.util.*

object CameraCaptureUtil {

    fun createImageUri(context: Context): ImageUriResult {
        val fileName = "IMG_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())}.jpg"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/TaskMajster")
            }
        }

        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        return ImageUriResult(uri, fileName)
    }
}