package com.prueba.galery.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prueba.galery.model.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): LiveData<Photo>

    @Query("SELECT * FROM photo")
    fun getAll(): LiveData<List<Photo>>

    @Insert
    fun insertAll(vararg photo: Photo)

    @Update
    fun update(photo: Photo)

    @Delete
    fun delete(photo: Photo)

}