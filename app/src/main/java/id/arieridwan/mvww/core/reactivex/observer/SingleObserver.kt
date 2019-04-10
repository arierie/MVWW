package id.arieridwan.mvww.core.reactivex.observer

import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by arieridwan on 10/04/19.
 */

class SingleObserver<T>(private val doOnSuccess: ((t: T) -> Unit),
                        private val doOnError: ((t: Throwable) -> Unit)) : DisposableSingleObserver<T>() {

    override fun onSuccess(t: T) {
        doOnSuccess(t)
    }

    override fun onError(e: Throwable) {
        doOnError(e)
    }

}