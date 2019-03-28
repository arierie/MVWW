package id.arieridwan.mvww.core.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import id.arieridwan.mvww.di.ViewModelFactory
import javax.inject.Inject

/**
 * Created by arieridwan on 26/12/18.
 */

abstract class BaseFragment: androidx.fragment.app.Fragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    abstract fun layoutResource(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource(), container, false)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment>? {
        return childFragmentInjector
    }

    protected fun navigateTo(@IdRes actionId: Int, arg: Parcelable? = null) {
        val bundle = arg?.let { Bundle().apply { putParcelable(KEY_ARG, it) } }
        findNavController().navigate(actionId, bundle)
    }

    companion object {
        const val KEY_ARG = "KEY_ARG"
    }

}