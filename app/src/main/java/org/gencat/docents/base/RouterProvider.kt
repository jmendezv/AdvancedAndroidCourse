package org.gencat.docents.base

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router

interface RouterProvider {

    fun getRouter(): Router

    fun initialScreen(): Controller

}