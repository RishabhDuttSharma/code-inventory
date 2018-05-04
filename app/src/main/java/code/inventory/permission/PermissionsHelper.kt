package code.inventory.permission

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import code.inventory.util.containsOnly

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 01-May-18.
 */
class PermissionsHelper private constructor(private val activity: Activity) {

    private val permissions: ArrayList<String> = ArrayList()
    private var callback: OnRequestPermissionsCallback? = null

    class Builder(activity: Activity) {

        private val permissionsHelper = PermissionsHelper(activity)

        fun addPermission(permission: String) = this
                .also { permissionsHelper.permissions.add(permission) }

        fun addPermissions(permissions: List<String>) = this
                .also { permissionsHelper.permissions.addAll(permissions) }

        fun setOnRequestPermissionsCallback(callback: OnRequestPermissionsCallback) = this
                .also { permissionsHelper.callback = callback }

        fun build() = permissionsHelper
    }


    fun checkRationaleAndRequestPermissions(requestCode: Int) {
        if (PermissionUtils.hasRationalePermissions(activity, permissions))
            callback?.showPermissionsRationale(requestCode)
        else requestPermissions(requestCode)
    }

    fun requestPermissions(requestCode: Int) =
            ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), requestCode)

    fun handleRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (grantResults.containsOnly(PackageManager.PERMISSION_GRANTED))
            callback?.onPermissionsGranted(requestCode)
        else callback?.showPermissionsRationale(requestCode)
    }

    interface OnRequestPermissionsCallback {

        fun onPermissionsGranted(requestCode: Int)

        fun showPermissionsRationale(requestCode: Int)
    }
}