package id.arieridwan.mvww.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import id.arieridwan.mvww.presentation.BaseApplication
import javax.inject.Singleton

/**
 * Created by arieridwan on 20/12/18.
 */

@Module(includes = [(AndroidInjectionModule::class), (ViewModelModule::class)])
class ApplicationModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideContext(application: BaseApplication): Context =
            application.applicationContext

}