package id.arieridwan.mvww.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import id.arieridwan.mvww.presentation.ui.detail.DetailFragment
import id.arieridwan.mvww.presentation.ui.detail.DetailViewModel
import id.arieridwan.mvww.presentation.ui.home.HomeFragment
import id.arieridwan.mvww.presentation.ui.home.HomeViewModel
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by arieridwan on 20/12/18.
 */

@Module
class ViewModelModule {

    @Suppress("UNCHECKED_CAST")
    @Provides
    @Singleton
    fun provideViewModelFactory(
            providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
        }
    }

    @Provides
    fun provideHomeViewModel(factory: ViewModelProvider.Factory,
                          target: HomeFragment): ViewModel {
        return ViewModelProviders.of(target, factory).get(HomeViewModel::class.java)
    }

    @Provides
    fun provideDetailViewModel(factory: ViewModelProvider.Factory,
                          target: DetailFragment): ViewModel {
        return ViewModelProviders.of(target, factory).get(DetailViewModel::class.java)
    }


}