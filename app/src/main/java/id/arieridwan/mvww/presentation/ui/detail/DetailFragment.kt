package id.arieridwan.mvww.presentation.ui.detail

import android.os.Bundle
import android.view.View
import id.arieridwan.mvww.core.ui.BaseFragment
import id.arieridwan.mvww.R
import id.arieridwan.mvww.presentation.model.MovieUiModel
import id.arieridwan.mvww.presentation.util.CommonUtils.getBackdropUrl
import id.arieridwan.mvww.presentation.util.loadFromUrl
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * Created by arieridwan on 26/12/18.
 */

class DetailFragment: BaseFragment() {

    override fun layoutResource(): Int = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        val data: MovieUiModel? = arguments?.getParcelable(KEY_ARG)
        data?.let {
            tv_title.text = it.title
            tv_overview.text = it.overview
            val url = getBackdropUrl(it.backdropPath)
            context?.let { ctx -> img_backdrop.loadFromUrl(ctx, url) }
        }
    }

}