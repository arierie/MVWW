package id.arieridwan.mvww.presentation.ui.base

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import id.arieridwan.mvww.di.ViewModelFactory
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

abstract class BaseActivity : AppCompatActivity(),
    HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<android.support.v4.app.Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun layoutResource(): Int

    override
    fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutResource())
    }

    override
    fun supportFragmentInjector(): AndroidInjector<android.support.v4.app.Fragment> {
        return supportFragmentInjector
    }

    override
    fun fragmentInjector(): AndroidInjector<Fragment> {
        return frameworkFragmentInjector
    }

}