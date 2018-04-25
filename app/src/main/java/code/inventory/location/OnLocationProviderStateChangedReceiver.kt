package code.inventory.location

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import code.inventory.FilteredBroadcastReceiver

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/25/2018.
 */
class OnLocationProviderStateChangedReceiver(private val listener: Listener?) : FilteredBroadcastReceiver() {

    override fun getAction() = LocationManager.PROVIDERS_CHANGED_ACTION

    override fun onFilteredReceive(context: Context?, intent: Intent) {
        listener?.onLocationStateChanged()
    }

    /**
     * Callback interface, invoked when Location State changes occur.
     */
    interface Listener {

        /**
         * Called when LocationProvider state is changed
         */
        fun onLocationStateChanged()
    }
}