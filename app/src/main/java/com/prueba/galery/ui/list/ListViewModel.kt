package com.prueba.galery.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prueba.galery.R
import com.prueba.galery.model.Photo
import com.prueba.galery.repository.PhotoRepository
import javax.inject.Inject

class ListViewModel @Inject constructor(private val photoRepository: PhotoRepository): ViewModel() {

    fun getPhotos(): LiveData<List<Photo>> {
        return photoRepository.getPhotos()
    }

}
