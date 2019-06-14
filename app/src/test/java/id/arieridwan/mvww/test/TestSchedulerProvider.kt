package id.arieridwan.mvww.test

import id.arieridwan.mvww.core.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by arieridwan on 09/04/19.
 */

class TestSchedulerProvider: BaseSchedulerProvider {

    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()

}