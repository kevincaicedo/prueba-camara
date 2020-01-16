package com.prueba.galery.ui.photo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import java.io.File
import javax.inject.Inject


class PhotoViewModel @Inject constructor(): ViewModel() {

    fun getBitmap(path: String): Bitmap {
        val imgFile = File(path)
        return BitmapFactory.decodeFile(imgFile.absolutePath)
    }
}
