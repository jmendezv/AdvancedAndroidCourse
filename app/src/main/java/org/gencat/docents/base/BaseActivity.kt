package org.gencat.docents.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import org.gencat.docents.R
import org.gencat.docents.di.Injector
import org.gencat.docents.di.ScreenInjector
import java.util.*
import javax.inject.Inject

private const val INSTANCE_ID_KEY = "instance_id"

abstract class BaseActivity : AppCompatActivity() {

    lateinit var instanceId: String
        private set

    private lateinit var router: Router

    @Inject
    lateinit var screenInjector: ScreenInjector

    override fun onCreate(savedInstanceState: Bundle?) {

        instanceId = savedInstanceState?.run {
            savedInstanceState.getString(INSTANCE_ID_KEY)
        } ?: UUID.randomUUID().toString()

        Injector.inject(this)
        setContentView(layoutRes())
        val screenContainer = findViewById<ViewGroup>(R.id.screen_container)
        screenContainer
                ?: throw NullPointerException("Activity must have a view with id: 'screen_container'")
        router = Conductor.attachRouter(this, screenContainer, savedInstanceState)
        monitorBackStack()
        super.onCreate(savedInstanceState)
    }

    private fun monitorBackStack() {
        router.addChangeListener(object : ControllerChangeHandler.ControllerChangeListener {
            override fun onChangeStarted(
                    to: Controller?,
                    from: Controller?,
                    isPush: Boolean,
                    container: ViewGroup,
                    handler: ControllerChangeHandler) {
            }

            override fun onChangeCompleted(
                    to: Controller?,
                    from: Controller?,
                    isPush: Boolean,
                    container: ViewGroup,
                    handler: ControllerChangeHandler) {

                /*
                * from - The old Controller or null if there was no Controller before this transition
                * isPush - True if this was a push operation, or false if it's a pop
                * When the user hits back (pop) the controller from is out form the back stack
                * The next time it shows up, we want a fresh component (new session)
                * */
                if (!isPush && from != null) {
                    Injector.clearComponent(from)
                }
            }

        })
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(INSTANCE_ID_KEY, instanceId)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Injector.clearComponent(this)
    }

}