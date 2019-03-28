package id.arieridwan.mvww.presentation.ui.home

import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.core.ui.BaseViewModel
import javax.inject.Inject

/**
 * Created by arieridwan on 26/12/18.
 */

class HomeViewModel @Inject constructor(private val loadMoviesUseCase: LoadMoviesUseCase)
    : BaseViewModel(loadMoviesUseCase)