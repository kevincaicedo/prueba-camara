package com.prueba.galery.di.module

import androidx.fragment.app.ListFragment
import com.prueba.galery.ui.camera.CameraFragment
import com.prueba.galery.ui.form.FormFragment
import com.prueba.galery.ui.menu.MenuFragment
import com.prueba.galery.ui.photo.PhotoFragment
import com.prueba.galery.ui.view.ViewFragment
import com.prueba.galery.ui.view.ViewFragmentDirections
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMenuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun contributeCameraFragment(): CameraFragment

    @ContributesAndroidInjector
    abstract fun contributePhotoFragment(): PhotoFragment

    @ContributesAndroidInjector
    abstract fun contributeFormFragment(): FormFragment

    @ContributesAndroidInjector
    abstract fun contributeViewFragment(): ViewFragment
}