package id.arieridwan.mvww.di.module

import dagger.Module
import dagger.Provides
import id.arieridwan.mvww.core.custom.schedulers.BaseSchedulerProvider
import id.arieridwan.mvww.core.custom.schedulers.SchedulerProvider
import javax.inject.Singleton

/**
 * Created by arieridwan on 09/04/19.
 */

@Module
class RxJavaModule {

    @Provides
    @Singleton
    fun provideScheduler(): BaseSchedulerProvider = SchedulerProvider()

}