package id.arieridwan.mvww.core.usecase

import id.arieridwan.mvww.core.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.addTo

/**
 * Created by arieridwan on 20/12/18.
 */

abstract class SingleUseCase<T, in Params>: BaseUseCase() {

    internal abstract fun buildUseCaseSingle(params: Params): Single<T>

    fun execute(observer: DisposableSingleObserver<T>,
                params: Params,
                schedulerProvider: BaseSchedulerProvider) {
        disposeLast()
        lastDisposable = buildUseCaseSingle(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeWith(observer)
            .addTo(compositeDisposable)
    }

}