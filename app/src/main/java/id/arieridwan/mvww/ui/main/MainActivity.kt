package id.arieridwan.mvww.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import id.arieridwan.mvww.R
import id.arieridwan.mvww.ui.base.BaseActivity
import id.arieridwan.mvww.core.DataRequestState
import id.arieridwan.mvww.core.State
import id.arieridwan.mvww.domain.entity.MovieViewParam
import id.arieridwan.mvww.ui.adapter.MoviesAdapter
import id.arieridwan.mvww.ui.util.CommonUtils.CATEGORY_POPULAR
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
        mViewModel.showMovies.observe(::getLifecycle, ::updateMoviesUI)
    }

    private fun updateMoviesUI(request: DataRequestState<List<MovieViewParam>>?) {
        request?.let { req ->
            when (req.state) {
                State.SUCCEEDED -> req.data?.let { movies -> moviesAdapter.setMovies(movies) }
                State.FAILED -> Toast.makeText(this, "Failed with error : ${req.error}", Toast.LENGTH_SHORT).show()
                State.COMPLETED -> swipe_refresh_layout.isRefreshing = false
            }
        }
    }

    override fun onItemClick(item: MovieViewParam) {
        // TODO navigate to detail page
    }

}