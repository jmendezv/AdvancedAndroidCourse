package org.gencat.docents.ui

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router

/*
* Conductor and its components
*
* - Controllers can be considered fragment-equivalence in Conductor
*
* - Routers are responsible for back stack management in Conductor
*
* - ControllerChangeHandler is responsible for rendering the view change
*   whenever a controller is pushed into or popped from a Router.
*   By default it uses SimpleSwapChangeHandler
*
* - RouterTransaction is used to define data about adding Controllers
*   into a Router.
*
* */
interface ScreenNavigator {

    /* return true if the screen was popped */
    fun pop(): Boolean

    fun goToRepoDetails(repoName: String, repoOwner: String)

}