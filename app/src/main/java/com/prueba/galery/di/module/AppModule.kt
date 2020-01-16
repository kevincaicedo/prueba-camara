package com.prueba.galery.di.module

import android.app.Application
import androidx.room.Room
import com.prueba.galery.db.GaleryDatabase
import com.prueba.galery.db.dao.PhotoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [MainActivityModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideGaleryDatabase(app: Application): GaleryDatabase {
        return Room
            .databaseBuilder(app, GaleryDatabase::class.java, "galery_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePhotoDao(db: GaleryDatabase): PhotoDao {
        return db.photoDao()
    }

}