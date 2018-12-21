package id.arieridwan.mvww.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.arieridwan.mvww.ui.main.MainActivity

/**
 * Created by arieridwan on 20/12/18.
 */

@Module(includes = [(ViewModelModule::class)])
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun mainActivityInjector(): MainActivity

}