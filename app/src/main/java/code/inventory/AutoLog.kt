package code.inventory

import android.util.Log

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/25/2018.
 */
object AutoLog {

    private val DEFAULT_TAG = "AutoLog"
    private val DEFAULT_MESSAGE = "null"
    private val enabled = BuildConfig.AUTOLOG_ENABLED

    fun <T, M> d(tag: T?, message: M?) {
        if (enabled) Log.d(getTag(tag), getMessage(message))
    }

    fun <T, M> e(tag: T?, message: M?) {
        if (enabled) Log.e(getTag(tag), getMessage(message))
    }

    fun <T, M> i(tag: T?, message: M?) {
        if (enabled) Log.i(getTag(tag), getMessage(message))
    }

    fun <T, M> v(tag: T?, message: M?) {
        if (enabled) Log.v(getTag(tag), getMessage(message))
    }

    fun <T, M> w(tag: T?, message: M?) {
        if (enabled) Log.w(getTag(tag), getMessage(message))
    }

    private fun <T> getTag(tag: T?) = getValue(tag, DEFAULT_TAG)

    private fun <M> getMessage(message: M?) = getValue(message, DEFAULT_MESSAGE)

    private fun <V> getValue(value: V?, defaultVal: String): String = value?.toString()
            ?: defaultVal
}