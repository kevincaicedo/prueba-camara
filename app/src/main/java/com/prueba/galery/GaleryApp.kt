package com.prueba.galery

import android.app.Application
import com.prueba.galery.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class GaleryApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

         DaggerAppComponent
            .builder()
            .applicationBind(this)
            .build().inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector

}