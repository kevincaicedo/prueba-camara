package com.prueba.galery.di.module

import android.app.Application
import androidx.room.Room
import com.prueba.galery.db.GaleryDatabase
import com.prueba.galery.db.dao.PhotoDao
import com.prueba.galery.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [MainActivityModule::class])
class AppModule {

    @Provides
    @ApplicationScope
    fun provideGaleryDatabase(app: Application): GaleryDatabase {
        return Room
            .databaseBuilder(app, GaleryDatabase::class.java, "galery_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @ApplicationScope
    fun providePhotoDao(db: GaleryDatabase): PhotoDao {
        return db.photoDao()
    }

}