package id.arieridwan.mvww.core.ui

import android.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

abstract class BaseActivity : AppCompatActivity(),
    HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    abstract fun layoutResource(): Int

    override
    fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutResource())
    }

    override
    fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> {
        return supportFragmentInjector
    }

    override
    fun fragmentInjector(): AndroidInjector<Fragment> {
        return frameworkFragmentInjector
    }

}