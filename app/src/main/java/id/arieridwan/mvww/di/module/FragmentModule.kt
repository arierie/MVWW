package id.arieridwan.mvww.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.arieridwan.mvww.ui.detail.DetailFragment
import id.arieridwan.mvww.ui.home.HomeFragment

/**
 * Created by arieridwan on 26/12/18.
 */

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun homeFragmentInjector(): HomeFragment

    @ContributesAndroidInjector
    abstract fun detailFragmentInjector(): DetailFragment

}