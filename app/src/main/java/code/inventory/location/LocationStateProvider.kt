package code.inventory.location

import android.content.Context
import android.location.LocationManager

/**
 * Provides helpers methods for Location States
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/25/2018.
 */
object LocationStateProvider {

    /**
     * @param context
     * @return true, if GPS-Provider is enabled, false otherwise
     */
    fun isGPSEnabled(context: Context) = isProviderEnabled(context, LocationManager.GPS_PROVIDER)

    /**
     * @param context
     * @return true, if Network-Provider is enabled, false otherwise
     */
    fun isNetworkEnabled(context: Context) = isProviderEnabled(context, LocationManager.NETWORK_PROVIDER)

    /**
     * @param context
     * @param provider the [LocationManager.*_PROVIDER]
     * @return true, if given LocationProvider is enabled, false otherwise
     */
    fun isProviderEnabled(context: Context, provider: String) = getLocationManager(context).isProviderEnabled(provider)

    /**
     * @param context
     * @return an instance of [LocationManager]
     */
    fun getLocationManager(context: Context) = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
}