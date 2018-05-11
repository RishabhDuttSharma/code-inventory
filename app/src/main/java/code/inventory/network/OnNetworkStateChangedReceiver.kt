package code.inventory.network

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import code.inventory.plugin.FilteredBroadcastReceiver

/**
 * Use [Context.registerReceiver()] to receive NetworkStateChange Broadcasts.
 * Do not declare in AndroidManifest for apps targeting API 27 or Higher.
 *
 * Use [ConnectivityManager.getActiveNetworkInfo()] to enquire NetworkInfo
 *
 * Developer: A1UGPQS2
 * [Rishabh Dutt Sharma]
 * <p/>
 * Organization: Airtel
 * [Agile Lab (KYC) - Africa]
 * <p/>
 * Dated: 4/25/2018.
 */
class OnNetworkStateChangedReceiver(private val listener: Listener?) : FilteredBroadcastReceiver() {

    override fun getAction() = CONNECTIVITY_ACTION

    override fun onFilteredReceive(context: Context?, intent: Intent) {
        listener?.onNetworkStateChanged(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false))
    }

    /**
     *  Callback interface to receive NetworkStateChanges
     */
    interface Listener {

        /**
         * Called when a change occurs in Network State (e.g., Connected, Disconnected)
         *
         * @param true, if internet connection lost, false otherwise
         */
        fun onNetworkStateChanged(noConnectivity: Boolean)
    }
}