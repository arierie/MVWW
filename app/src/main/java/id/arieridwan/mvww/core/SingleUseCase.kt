package id.arieridwan.mvww.core

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Created by arieridwan on 20/12/18.
 */

abstract class SingleUseCase<T, in Params>: BaseUseCase() {

    internal abstract fun buildUseCaseSingle(params: Params): Single<T>

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {},
        onSubscribe: ((d: Disposable) -> Unit),
        params: Params
    ) {
        disposeLast()
        lastDisposable = buildUseCaseSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(onSubscribe)
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)
            .addTo(compositeDisposable)
    }

}