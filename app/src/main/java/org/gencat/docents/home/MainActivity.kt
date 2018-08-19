package org.gencat.docents.home

import com.bluelinelabs.conductor.Controller
import org.gencat.docents.R
import org.gencat.docents.base.BaseActivity
import org.gencat.docents.trending.TrendingRepoController

class MainActivity : BaseActivity() {

    override fun layoutRes(): Int = R.layout.activity_main

    override fun initialScreen(): Controller =
            TrendingRepoController()

}
