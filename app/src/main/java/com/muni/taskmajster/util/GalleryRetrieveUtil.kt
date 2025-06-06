package com.muni.taskmajster.util

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.muni.taskmajster.model.data.ImageUriResult

object GalleryRetrieveUtil {

    fun getImagesByNameFromTaskMajsterFolder(
        context: Context,
        targetFilenames: List<String>
    ): List<ImageUriResult> {
        val imageUriList = mutableListOf<ImageUriResult>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.RELATIVE_PATH
        )

        val selection = "${MediaStore.Images.Media.RELATIVE_PATH} = ?"
        val selectionArgs = arrayOf("Pictures/TaskMajster/")

        val queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        context.contentResolver.query(
            queryUri,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val name = cursor.getString(nameColumn)
                if (targetFilenames.contains(name)) {
                    val id = cursor.getLong(idColumn)
                    val contentUri = ContentUris.withAppendedId(queryUri, id)
                    imageUriList.add(ImageUriResult(contentUri, name))
                }
            }
        }

        return imageUriList
    }
}