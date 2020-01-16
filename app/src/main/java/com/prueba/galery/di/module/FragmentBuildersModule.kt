package com.prueba.galery.di.module

import androidx.fragment.app.ListFragment
import com.prueba.galery.ui.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMenuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment

}