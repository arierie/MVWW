package id.arieridwan.mvww.core.state

/**
 * Created by arieridwan on 20/12/18.
 */

data class AsyncRequest<T> constructor(val state: AsyncState, val data: T? = null, val error: String? = null){
    constructor(state: AsyncState, error: String? = null, data: T? = null) : this(state, data, error)
}