package com.prueba.galery.di.component

import android.app.Application
import com.prueba.galery.GaleryApp
import com.prueba.galery.di.module.AppModule
import com.prueba.galery.di.module.FragmentBuildersModule
import com.prueba.galery.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        FragmentBuildersModule::class
    ]
)
interface AppComponent {

    fun inject(galeryApp: GaleryApp)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder

    }
}