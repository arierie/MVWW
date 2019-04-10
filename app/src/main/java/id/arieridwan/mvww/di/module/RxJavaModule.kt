package id.arieridwan.mvww.di.module

import dagger.Module
import dagger.Provides
import id.arieridwan.mvww.core.reactivex.schedulers.*
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