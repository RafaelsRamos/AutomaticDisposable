package com.spartancookie.automaticdisposable.disposable

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.FloatRange
import java.util.*
import java.util.concurrent.Callable

/**
 * Automatic disposable
 *
 * @param T Type of object that will be stored
 * @property secondsInactive   Seconds inactive
 * @property callable          Callable that used to retrieve a valid instance of [T]
 * @constructor Create Automatic disposable that destroys an object after [secondsInactive] of not being used
 */
class AutomaticDisposable<T>(@FloatRange(from = 0.0, to = 216000.0) var secondsInactive: Float, private val callable: Callable<T>? = null, private val name: String, private val context: Context) {

    private var disposeTask: TimerTask? = null

    private var value: T? = null

    //------------------ Controls ------------------

    fun setValue(value: T) {
        toastShort("Setting new value - Postponing $name timer for ${secondsInactive/1000F} seconds")
        scheduleDestroy()
        this.value = value
    }

    fun get(): T? {
        return value?.run {
            toastShort("Has valid instance - Postponing $name timer for ${secondsInactive/1000F} seconds")
            scheduleDestroy()
            this
        } ?: fetchValidInstance()
    }

    fun changeInactiveSeconds(secondsInactive: Float) {
        this.secondsInactive = secondsInactive
    }

    //---------------- Schedule destroy ----------------

    private fun scheduleDestroy() {
        disposeTask?.cancel()
        disposeTask = object: TimerTask() { override fun run() {
            value = null
            toastShort("Clearing VAR! $name")
        } }
        Timer().schedule(disposeTask, secondsInactive.toLong())
    }

    //---------------- Helper ----------------

    private fun fetchValidInstance(): T? {
        toastShort("Trying to fetch new instance...")
        callable?.run {
            toastShort("Found a backup instance :)")
            return call()
        }
        toastShort("Not backup instance set :(")
        return null
        //return callable?.call()
    }

    private fun toastShort(string: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
        }
    }
}