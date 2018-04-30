package code.inventory.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/30/2018.
 */
object PermissionManager {

    // PermissionHelper.getDeniedPermissions(permissions)
    //
    // if(deniedPermissions.isNotEmpty())
    //      PermissionsHelper.with(this) .setPermissionsRationaleHandler(this)
    //                                   .addPermissions(deniedPermissions) .help()
    //
    // user permissionHelper instance in onRequestPermissionsResult() to handle permissions
    //
    // shouldShowPermissionsRationale will give callback to PermissionsRationaleHandler.


    fun checkPermissions(activity: Activity, vararg permissions: String) {

        if (filterDeniedPermissions(activity, permissions.toList()).isNotEmpty()) {
            ActivityCompat.shouldShowRequestPermissionRationale(activity, )
        }
    }

    fun isPermissionGranted(context: Context, permission: String): Boolean =
            ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    fun filterDeniedPermissions(context: Context, permissions: List<String>): List<String> = permissions.mapNotNull {
        if (isPermissionGranted(context, it)) null else it
    }
}