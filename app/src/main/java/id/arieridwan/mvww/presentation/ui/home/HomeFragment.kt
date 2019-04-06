package id.arieridwan.mvww.presentation.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast

import id.arieridwan.mvww.R
import id.arieridwan.mvww.presentation.model.MovieUiModel
import id.arieridwan.mvww.core.ui.BaseFragment
import id.arieridwan.mvww.core.state.AsyncRequest
import kotlinx.android.synthetic.main.fragment_home.*
import id.arieridwan.mvww.core.state.AsyncState.*

class HomeFragment : BaseFragment(), MovieAdapter.MoviesListener {

    override fun layoutResource(): Int = R.layout.fragment_home

    private val movieAdapter: MovieAdapter = MovieAdapter()

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        swipe_refresh_layout.setOnRefreshListener {  }
    }


    private fun initView() {
        movieAdapter.setListener(this)
        recycler_view.apply {
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
            adapter = movieAdapter
        }
    }


    private fun showLoading() {
        if (!swipe_refresh_layout.isRefreshing) swipe_refresh_layout.isRefreshing = true
    }

    private fun hideLoading() {
        if (swipe_refresh_layout.isRefreshing) swipe_refresh_layout.isRefreshing = false
    }

    private fun updateMoviesUI(request: AsyncRequest<List<MovieUiModel>>) {
        when (request.state) {
            UNINITIALIZED -> {}
            LOADING -> request.data?.let { movies -> movieAdapter.setMovies(movies) }
            SUCCESS -> Toast.makeText(context, "Failed with error : ${request.error}", Toast.LENGTH_SHORT).show()
            FAIL -> {}
        }
    }

    override fun onItemClick(item: MovieUiModel) {
        navigateTo(R.id.action_home_to_detailFragment, item)
    }

}