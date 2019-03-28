package id.arieridwan.mvww.presentation.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast

import id.arieridwan.mvww.R
import id.arieridwan.mvww.presentation.entity.MovieViewParam
import id.arieridwan.mvww.presentation.ui.adapter.MoviesAdapter
import id.arieridwan.mvww.core.ui.BaseFragment
import id.arieridwan.mvww.core.state.DataRequestState
import kotlinx.android.synthetic.main.fragment_home.*
import id.arieridwan.mvww.core.state.State.SUCCEEDED
import id.arieridwan.mvww.core.state.State.FAILED
import id.arieridwan.mvww.core.state.State.COMPLETED

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
        swipe_refresh_layout.setOnRefreshListener {  }
    }


    private fun initView() {
        moviesAdapter.setListener(this)
        recycler_view.apply {
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
            adapter = moviesAdapter
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