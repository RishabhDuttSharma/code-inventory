package code.inventory.location

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/30/2018.
 */
object LocationConstants {

    const val REQ_LOCATION_PERMISSION = 2001
    const val REQ_LOCATION_SETTINGS = 2002

    private const val PERM_ACCESS_COARSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION
    private const val PERM_ACCESS_FINE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION

    val LOCATION_PERMISSIONS = arrayListOf(PERM_ACCESS_COARSE_LOCATION, PERM_ACCESS_FINE_LOCATION)
}