package id.arieridwan.mvww.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import id.arieridwan.mvww.BaseApplication
import id.arieridwan.mvww.core.BaseSchedulerProvider
import id.arieridwan.mvww.core.SchedulerProvider
import javax.inject.Singleton

/**
 * Created by arieridwan on 20/12/18.
 */

@Module(includes = [(AndroidInjectionModule::class), (ViewModelModule::class)])
open class ApplicationModule {

    @Singleton
    @Provides
    open fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    open fun provideScheduler(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Singleton
    @Provides
    open fun provideContext(application: BaseApplication): Context {
        return application.applicationContext
    }

}