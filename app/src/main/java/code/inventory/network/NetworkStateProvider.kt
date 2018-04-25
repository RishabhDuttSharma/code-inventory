package code.inventory.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.support.annotation.RequiresPermission

/**
 * Developer: A1UGPQS2
 * [Rishabh Dutt Sharma]
 * <p/>
 * Organization: Airtel
 * [Agile Lab (KYC) - Africa]
 * <p/>
 * Dated: 4/25/2018.
 */
object NetworkStateProvider {

    /**
     * Declare [android.permission.ACCESS_NETWORK_STATE] in AndroidManifest
     *
     * @param context
     * @return true if Cellular Data is ENABLED, false otherwise
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun isCellularDataEnabled(context: Context): Boolean {

        val connMgr = getConnectivityManager(context)

        val connMgrClass = Class.forName(connMgr.javaClass.name)
        val connMgrMethod = connMgrClass.getDeclaredMethod("getMobileDataEnabled")
                .also { it.isAccessible = true }
        return connMgrMethod.invoke(connMgr) as Boolean
    }

    /**
     * Declare [android.permission.ACCESS_WIFI_STATE] in AndroidManifest
     *
     * @param context
     * @return true, if Wi-Fi is ENABLED, false otherwise
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_WIFI_STATE)
    fun isWifiEnabled(context: Context) = (context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager).isWifiEnabled


    /**
     * Declare [android.permission.ACCESS_NETWORK_STATE] in AndroidManifest
     *
     * @param context
     * @return true, if Connected to Internet, false otherwise
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun isInternetConnected(context: Context) = getConnectivityManager(context).activeNetworkInfo?.isConnected

    private fun getConnectivityManager(context: Context) = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}