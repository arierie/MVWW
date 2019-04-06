package id.arieridwan.mvww.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.arieridwan.mvww.di.ViewModelFactory
import id.arieridwan.mvww.di.scope.ViewModelKey
import id.arieridwan.mvww.presentation.ui.detail.DetailViewModel
import id.arieridwan.mvww.presentation.ui.home.HomeViewModel

/**
 * Created by arieridwan on 20/12/18.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

}