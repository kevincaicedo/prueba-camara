package com.prueba.galery.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prueba.galery.db.dao.PhotoDao
import com.prueba.galery.model.Photo

@Database(
    entities = [Photo::class],
    version = 1,
    exportSchema = false
)
abstract class GaleryDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}