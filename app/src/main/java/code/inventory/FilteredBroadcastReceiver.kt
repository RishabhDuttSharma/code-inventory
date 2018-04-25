package code.inventory

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/25/2018.
 */
abstract class FilteredBroadcastReceiver : BroadcastReceiver() {

    abstract fun getAction(): String

    abstract fun onFilteredReceive(context: Context?, intent: Intent)

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == getAction()) onFilteredReceive(context, intent)
    }

    /**
     * Call this method to get this instance registered through [context] with appropriate [ACTION]
     *
     * @param context
     */
    fun register(context: Context) = context.registerReceiver(this, IntentFilter(getAction()))
}