package id.arieridwan.mvww.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import id.arieridwan.mvww.presentation.BaseApplication
import id.arieridwan.mvww.di.module.*
import javax.inject.Singleton

/**
 * Created by arieridwan on 20/12/18.
 */

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (ActivityModule::class),
    (ApplicationModule::class),
    (RepositoryModule::class),
    (UseCaseModule::class),
    (NetworkModule::class),
    (DaoModule::class)])
interface ApplicationComponent {

    fun inject(baseApplication: BaseApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): ApplicationComponent

    }

}