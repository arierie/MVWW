package id.arieridwan.mvww.core.reactivex.observer

import io.reactivex.observers.DisposableObserver

/**
 * Created by arieridwan on 10/04/19.
 */

class ObservableObserver<T>(val doOnNext: ((t: T) -> Unit),
                            val doOnError: ((t: Throwable) -> Unit),
                            val doOnComplete: (() -> Unit)) : DisposableObserver<T>() {

    override fun onComplete() {
        doOnComplete()
    }

    override fun onNext(t: T) {
        doOnNext(t)
    }

    override fun onError(e: Throwable) {
        doOnError(e)
    }

}