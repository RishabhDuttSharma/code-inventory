package code.inventory.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 01-May-18.
 */
object PermissionUtils {

    fun isPermissionGranted(context: Context, permission: String) =
            ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    fun isPermissionsGranted(context: Context, permissions: List<String>) =
            filterDeniedPermissions(context, permissions).isEmpty()

    fun filterDeniedPermissions(context: Context, permissions: List<String>) = permissions.mapNotNull {
        if (isPermissionGranted(context, it)) null else it
    }

    fun filterRationalePermissions(activity: Activity, permissions: List<String>) = permissions.mapNotNull {
        if (shouldShowRequestPermissionRationale(activity, it)) it else null
    }
}