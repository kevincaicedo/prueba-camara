package com.prueba.galery.ui.form

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prueba.galery.model.Photo
import com.prueba.galery.repository.PhotoRepository
import java.io.File
import javax.inject.Inject

class FormViewModel @Inject constructor(photoRepository: PhotoRepository) : ViewModel() {

    private val photoRepository = photoRepository

    fun savePhoto(photo: Photo){
        photoRepository.savePhoto(photo)
    }

    fun getPhotos(): LiveData<List<Photo>> {
        return photoRepository.getPhotos()
    }

    fun getBitmap(path: String): Bitmap {
        val imgFile = File(path)
        return BitmapFactory.decodeFile(imgFile.absolutePath)
    }

}
