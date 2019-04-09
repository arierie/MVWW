package id.arieridwan.mvww.di.component

import dagger.BindsInstance
import dagger.Component
import id.arieridwan.mvww.di.module.*
import id.arieridwan.mvww.presentation.BaseApplication
import id.arieridwan.mvww.test.BaseTest
import javax.inject.Singleton

/**
 * Created by arieridwan on 09/04/19.
 */

@Singleton
@Component(modules = [(ApplicationModule::class),
    (RxJavaModule::class),
    (RepositoryModule::class),
    (UseCaseModule::class),
    (NetworkModule::class),
    (DaoModule::class)])
interface TestApplicationComponent {
    fun inject(baseTest: BaseTest)

    @Component.Builder
    interface Builder {

        fun applicationModule(applicationModule: ApplicationModule): Builder

        @BindsInstance
        fun application(baseTest: BaseTest): Builder

        fun build(): TestApplicationComponent

    }

}