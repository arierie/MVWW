package id.arieridwan.mvww.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.arieridwan.mvww.di.ViewModelFactory
import id.arieridwan.mvww.di.annotation.ViewModelKey
import id.arieridwan.mvww.ui.detail.DetailViewModel
import id.arieridwan.mvww.ui.home.HomeViewModel
import id.arieridwan.mvww.ui.main.MainViewModel

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