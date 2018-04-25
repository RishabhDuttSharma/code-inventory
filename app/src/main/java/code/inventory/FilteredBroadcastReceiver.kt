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

    /**
     * @return Define the ACTION for receiving & filtration of required Broadcasts
     */
    abstract fun getAction(): String

    /**
     * Called when a Broadcast is received with given ACTION (specified by [getAction()])
     *
     * @param context
     * @param intent
     */
    abstract fun onFilteredReceive(context: Context?, intent: Intent)

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == getAction()) onFilteredReceive(context, intent)
    }

    /**
     * Call this method to get this instance registered through [context] with appropriate ACTION
     *
     * @param context
     */
    fun register(context: Context): Intent = context.registerReceiver(this, IntentFilter(getAction()))

    /**
     * Call this method to get this instance unregistered from [context]
     *
     * @param context
     */
    fun unregister(context: Context) = context.unregisterReceiver(this)
}