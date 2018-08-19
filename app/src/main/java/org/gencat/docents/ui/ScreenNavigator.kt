package org.gencat.docents.ui

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router

interface ScreenNavigator {

    fun initWithRouter(router: Router, rootScreen: Controller)

    /* return true if the screen was popped */
    fun pop(): Boolean

    /* clear any references when the activity is destroyed  */
    fun clear(): Unit

}