package id.arieridwan.mvww.core.state

/**
 * Created by arieridwan on 07/04/19.
 */

sealed class Async<out T> {

    object Uninitialized : Async<Nothing>()

    class Loading<out T> : Async<T>()

    data class Success<out T>(val value: T) : Async<T>()

    data class Fail<out T>(val error: Throwable) : Async<T>()

    class Complete<out T> : Async<T>()

}