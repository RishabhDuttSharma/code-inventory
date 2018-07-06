package code.inventory.plugin

import android.util.Log
import code.inventory.BuildConfig

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/25/2018.
 */
object AutoLog {

    private const val enabled = BuildConfig.AUTOLOG_ENABLED

    private val DEFAULT_TAG = "AutoLog"
    private val DEFAULT_MESSAGE = "null"

    fun <T> d(tag: String? = DEFAULT_TAG, message: T? = null) {
        if (enabled) Log.d(getTag(tag), getMessage(message))
    }

    fun <T> e(tag: String? = DEFAULT_TAG, message: T? = null) {
        if (enabled) Log.e(getTag(tag), getMessage(message))
    }

    fun <T> i(tag: String? = DEFAULT_TAG, message: T? = null) {
        if (enabled) Log.i(getTag(tag), getMessage(message))
    }

    fun <T> v(tag: String? = DEFAULT_TAG, message: T? = null) {
        if (enabled) Log.v(getTag(tag), getMessage(message))
    }

    fun <T> w(tag: String? = DEFAULT_TAG, message: T? = null) {
        if (enabled) Log.w(getTag(tag), getMessage(message))
    }

    private fun getTag(tag: String?) = tag ?: DEFAULT_TAG

    private fun <T> getMessage(message: T?) = message?.toString() ?: DEFAULT_MESSAGE
}