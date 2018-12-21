package id.arieridwan.mvww.core

/**
 * Created by arieridwan on 20/12/18.
 */

data class DataRequestState<T>(val state: State, val data: T? = null, val error: String? = null)

enum class State{
    SUCCEEDED,
    FAILED,
    COMPLETED
}