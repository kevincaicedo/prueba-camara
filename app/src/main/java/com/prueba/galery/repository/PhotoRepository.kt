package com.prueba.galery.repository

import androidx.lifecycle.LiveData
import com.prueba.galery.db.dao.PhotoDao
import com.prueba.galery.model.Photo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(photoDao: PhotoDao) {

    private val photoDao: PhotoDao = photoDao

    val allPhoto: LiveData<List<Photo>> = photoDao.getAll()

    fun savePhoto(photo: Photo){
        photoDao.insertAll(photo)
    }

    fun getPhotos(): LiveData<List<Photo>>{
        return allPhoto
    }

}