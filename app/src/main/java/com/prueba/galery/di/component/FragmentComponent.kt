package com.prueba.galery.di.component

import com.prueba.galery.di.module.FragmentBuildersModule
import com.prueba.galery.di.module.ViewModelModule
import com.prueba.galery.di.scope.FragmentScope
import com.prueba.galery.ui.main.MainActivity
import dagger.Component

@FragmentScope
@Component(
    modules = [
        ViewModelModule::class,
        FragmentBuildersModule::class
    ],
    dependencies = [AppComponent::class]
)
interface FragmentComponent {
    fun inject(mainActivity: MainActivity)
}