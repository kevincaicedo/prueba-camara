package com.prueba.galery.di.component

import android.app.Application
import com.prueba.galery.GaleryApp
import com.prueba.galery.di.module.AppModule
import com.prueba.galery.di.module.FragmentBuildersModule
import com.prueba.galery.di.module.ViewModelModule
import com.prueba.galery.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class
    ]
)
interface AppComponent: AndroidInjector<GaleryApp> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder

    }
}