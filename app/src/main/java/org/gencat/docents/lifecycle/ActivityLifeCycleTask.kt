package org.gencat.docents.lifecycle

import android.support.v7.app.AppCompatActivity

interface ActivityLifeCycleTask {

    fun onCreate(activity: AppCompatActivity): Unit {

    }

    fun onStart(activity: AppCompatActivity): Unit {

    }

    fun onResume(activity: AppCompatActivity): Unit {

    }

    fun onPause(activity: AppCompatActivity): Unit {

    }

    fun onStop(activity: AppCompatActivity): Unit {

    }

    fun onDestroy(activity: AppCompatActivity): Unit {

    }

}