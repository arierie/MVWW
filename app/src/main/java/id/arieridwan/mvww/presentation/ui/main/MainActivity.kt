package id.arieridwan.mvww.presentation.ui.main

import androidx.navigation.findNavController
import id.arieridwan.mvww.R
import id.arieridwan.mvww.core.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun layoutResource(): Int = R.layout.activity_main

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

}