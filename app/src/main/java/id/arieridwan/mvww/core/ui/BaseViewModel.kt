package id.arieridwan.mvww.core.ui

import androidx.lifecycle.ViewModel
import id.arieridwan.mvww.core.usecase.BaseUseCase

/**
 * Created by arieridwan on 20/12/18.
 */

abstract class BaseViewModel(vararg useCases: BaseUseCase) : ViewModel() {

    private var useCaseList: MutableList<BaseUseCase> = mutableListOf()

    init {
        useCaseList.addAll(useCases)
    }

    override fun onCleared() {
        super.onCleared()
        useCaseList.forEach { it.dispose() }
    }

}