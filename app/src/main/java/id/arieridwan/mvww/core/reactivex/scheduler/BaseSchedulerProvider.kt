package id.arieridwan.mvww.core.reactivex.scheduler

import io.reactivex.Scheduler

/**
 * Created by arieridwan on 08/04/19.
 */

interface BaseSchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}