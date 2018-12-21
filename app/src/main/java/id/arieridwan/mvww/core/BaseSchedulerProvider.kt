package id.arieridwan.mvww.core

import io.reactivex.Scheduler

/**
 * Created by arieridwan on 20/12/18.
 */

interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

}