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
import id.arieridwan.mvww.presentation.ui.main.MainViewModel

/**
 * Created by arieridwan on 20/12/18.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}