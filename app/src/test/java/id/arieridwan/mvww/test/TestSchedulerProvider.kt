package id.arieridwan.mvww.test

import id.arieridwan.mvww.core.custom.schedulers.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * Created by arieridwan on 09/04/19.
 */

class TestSchedulerProvider: BaseSchedulerProvider {

    private val testScheduler: TestScheduler = TestScheduler()

    override fun computation(): Scheduler = testScheduler

    override fun io(): Scheduler = testScheduler

    override fun ui(): Scheduler = testScheduler

}