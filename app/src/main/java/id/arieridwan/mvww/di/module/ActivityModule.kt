package id.arieridwan.mvww.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.arieridwan.mvww.presentation.ui.main.MainActivity

/**
 * Created by arieridwan on 20/12/18.
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun mainActivityInjector(): MainActivity

}