package code.inventory.location

import com.google.android.gms.location.LocationRequest

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 02-May-18.
 */
enum class LocationSetting(val priority: Int, val updateInterval: Long, val fastestUpdateInterval: Long) {

    // GPS, Network (GSM, WiFi), BLE
    HIGH_ACCURACY(LocationRequest.PRIORITY_HIGH_ACCURACY, 5_000, 3_000),

    // No GPS
    BALANCED_POWER_ACCURACY(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, 10_000, 5_000),
    LOW_POWER(LocationRequest.PRIORITY_LOW_POWER, 15_000, 7_000),
    NO_POWER(LocationRequest.PRIORITY_NO_POWER, 20_000, 10_000),
}