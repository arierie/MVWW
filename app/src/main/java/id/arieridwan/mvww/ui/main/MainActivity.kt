package id.arieridwan.mvww.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import id.arieridwan.mvww.R
import id.arieridwan.mvww.ui.base.BaseActivity
import id.arieridwan.mvww.ui.base.state.DataRequestState
import id.arieridwan.mvww.ui.base.state.State
import id.arieridwan.mvww.domain.entity.MovieViewParam
import id.arieridwan.mvww.ui.base.state.ScreenState
import id.arieridwan.mvww.ui.util.CommonUtils.CATEGORY_POPULAR
import id.arieridwan.mvww.ui.util.nonNullObserve
import id.arieridwan.mvww.ui.util.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MoviesAdapter.MoviesListener {

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun layoutResource(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initObserver()

        mViewModel.loadMovies(CATEGORY_POPULAR, 1)
        swipe_refresh_layout.setOnRefreshListener { mViewModel.loadMovies(CATEGORY_POPULAR, 1) }
    }

    private fun initView() {
        moviesAdapter.setListener(this)
        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
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
            ScreenState.AVAILABLE -> {
                hideLoading()
                showAvailablePage()
            }
            ScreenState.BLANK -> {
                hideLoading()
                showBlankPage()
            }
        }
    }

    private fun showLoading() {
        if (!swipe_refresh_layout.isRefreshing) swipe_refresh_layout.isRefreshing = true
    }

    private fun hideLoading() {
        if (swipe_refresh_layout.isRefreshing) swipe_refresh_layout.isRefreshing = false
    }

    private fun showBlankPage() {
        recycler_view.visible()
    }

    private fun showAvailablePage() {
        recycler_view.visible()
    }

    private fun updateMoviesUI(request: DataRequestState<List<MovieViewParam>>) {
        when (request.state) {
            State.SUCCEEDED -> request.data?.let { movies -> moviesAdapter.setMovies(movies) }
            State.FAILED -> Toast.makeText(this, "Failed with error : ${request.error}", Toast.LENGTH_SHORT).show()
            State.COMPLETED -> hideLoading()
        }
    }

    override fun onItemClick(item: MovieViewParam) {
        // TODO navigate to detail page
    }

}