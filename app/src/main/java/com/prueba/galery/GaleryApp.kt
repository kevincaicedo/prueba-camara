package com.prueba.galery

import android.app.Activity
import android.app.Application
import com.prueba.galery.di.component.AppComponent
import com.prueba.galery.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class GaleryApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerAppComponent
            .builder()
            .applicationBind(this)
            .build()

        applicationComponent.inject(this)
    }

    companion object {
        operator fun get(activity: Activity): GaleryApp? {
            return activity.application as GaleryApp
        }
    }

    fun getApplicationComponent(): AppComponent? {
        return applicationComponent
    }

    override fun androidInjector() = dispatchingAndroidInjector

}