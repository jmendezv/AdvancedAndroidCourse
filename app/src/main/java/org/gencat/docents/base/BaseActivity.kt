package org.gencat.docents.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.gencat.docents.di.Injector
import java.util.*

private const val INSTANCE_ID_KEY = "instance_id"

abstract class BaseActivity : AppCompatActivity() {

    lateinit var instanceId: String
        private set

    override fun onCreate(savedInstanceState: Bundle?) {

        instanceId = savedInstanceState?.run {
            savedInstanceState.getString(INSTANCE_ID_KEY)
        } ?: UUID.randomUUID().toString()

        Injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(INSTANCE_ID_KEY, instanceId)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(isFinishing) Injector.clearComponent(this)
    }

}