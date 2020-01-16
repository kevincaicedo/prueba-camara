package com.prueba.galery.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prueba.galery.di.qualifier.ViewModelKey
import com.prueba.galery.di.scope.FragmentScope
import com.prueba.galery.ui.camera.CameraViewModel
import com.prueba.galery.ui.form.FormViewModel
import com.prueba.galery.ui.list.ListViewModel
import com.prueba.galery.ui.menu.MenuViewModel
import com.prueba.galery.ui.photo.PhotoViewModel
import com.prueba.galery.ui.view.ViewViewModel
import com.prueba.galery.viewmodel.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(FormViewModel::class)
    abstract fun bindFormViewModel(formViewModel: FormViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(MenuViewModel::class)
    abstract fun bindMenuViewModel(menuViewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(CameraViewModel::class)
    abstract fun bindCameraViewModel(cameraViewModel: CameraViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(PhotoViewModel::class)
    abstract fun bindPhotoViewModel(photoViewModel: PhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(ViewViewModel::class)
    abstract fun bindViewViewModel(viewViewModel: ViewViewModel): ViewModel

    @Binds
    @FragmentScope
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}