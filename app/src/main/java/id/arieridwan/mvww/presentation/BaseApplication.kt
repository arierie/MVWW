package id.arieridwan.mvww.presentation

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import id.arieridwan.mvww.di.component.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

class BaseApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initInjector()
        initTimber()
    }

    private fun initInjector() {
        DaggerApplicationComponent.builder().application(this)
            .build().inject(this)
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}