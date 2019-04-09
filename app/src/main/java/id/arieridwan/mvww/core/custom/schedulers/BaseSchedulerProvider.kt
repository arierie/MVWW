package id.arieridwan.mvww.core.custom.schedulers

import io.reactivex.Scheduler

/**
 * Created by arieridwan on 08/04/19.
 */

interface BaseSchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}