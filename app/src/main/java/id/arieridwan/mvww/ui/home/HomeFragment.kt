package id.arieridwan.mvww.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast

import id.arieridwan.mvww.R
import id.arieridwan.mvww.domain.entity.MovieViewParam
import id.arieridwan.mvww.ui.adapter.MoviesAdapter
import id.arieridwan.mvww.ui.base.BaseFragment
import id.arieridwan.mvww.ui.base.state.DataRequestState
import id.arieridwan.mvww.ui.base.state.ScreenState
import kotlinx.android.synthetic.main.fragment_home.*
import id.arieridwan.mvww.ui.base.state.State.SUCCEEDED
import id.arieridwan.mvww.ui.base.state.State.FAILED
import id.arieridwan.mvww.ui.base.state.State.COMPLETED
import id.arieridwan.mvww.ui.util.CommonUtils
import id.arieridwan.mvww.ui.util.nonNullObserve

class HomeFragment : BaseFragment(), MoviesAdapter.MoviesListener {

    override fun layoutResource(): Int = R.layout.fragment_home

    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()

        mViewModel.loadMovies(CommonUtils.CATEGORY_POPULAR, 1)
        swipe_refresh_layout.setOnRefreshListener { mViewModel.loadMovies(CommonUtils.CATEGORY_POPULAR, 1) }
    }


    private fun initView() {
        moviesAdapter.setListener(this)
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviesAdapter
        }
    }

    private fun initObserver() {
        mViewModel.showMovies.nonNullObserve(this, ::updateMoviesUI)
        mViewModel.screenChange.nonNullObserve(this, ::determineScreenState)
    }

    private fun determineScreenState(state: ScreenState) {
        when (state) {
            ScreenState.LOADING -> showLoading()
            ScreenState.AVAILABLE -> hideLoading()
            ScreenState.BLANK -> hideLoading()
        }
    }

    private fun showLoading() {
        if (!swipe_refresh_layout.isRefreshing) swipe_refresh_layout.isRefreshing = true
    }

    private fun hideLoading() {
        if (swipe_refresh_layout.isRefreshing) swipe_refresh_layout.isRefreshing = false
    }

    private fun updateMoviesUI(request: DataRequestState<List<MovieViewParam>>) {
        when (request.state) {
            SUCCEEDED -> request.data?.let { movies -> moviesAdapter.setMovies(movies) }
            FAILED -> Toast.makeText(context, "Failed with error : ${request.error}", Toast.LENGTH_SHORT).show()
            COMPLETED -> {}
        }
    }

    override fun onItemClick(item: MovieViewParam) {
        navigateTo(R.id.action_home_to_detailFragment, item)
    }

}