package code.inventory.location

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import code.inventory.FilteredBroadcastReceiver

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/25/2018.
 */
class OnLocationStateChangedReceiver(private val listener: Listener) : FilteredBroadcastReceiver() {

    override fun getAction() = LocationManager.PROVIDERS_CHANGED_ACTION

    override fun onFilteredReceive(context: Context?, intent: Intent) {

    }

    interface Listener {

        fun onLocationStateChanged(gpsEnabled: Boolean, networkEnabled: Boolean)
    }
}