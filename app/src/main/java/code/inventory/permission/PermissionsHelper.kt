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

        fun addPermission(permission: String) = this.also { permissionsHelper.permissions.add(permission) }

        fun addPermissions(permissions: List<String>) = this.also { permissionsHelper.permissions.addAll(permissions) }

        fun setOnRequestPermissionsCallback(callback: OnRequestPermissionsCallback) = this.also {
            permissionsHelper.callback = callback
        }

        fun build() = permissionsHelper
    }

    fun checkRationaleAndRequestPermissions(requestCode: Int) {
        val rationalePermissions = PermissionUtils.filterRationalePermissions(activity, permissions)
        if (rationalePermissions.isNotEmpty()) callback?.showPermissionsRationale(requestCode)
        else ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), requestCode)
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (grantResults.containsOnly(PackageManager.PERMISSION_GRANTED)) callback?.onAllowedAllPermissions()
        else callback?.showPermissionsRationale(requestCode)
    }

    interface OnRequestPermissionsCallback {

        fun onAllowedAllPermissions()

        fun showPermissionsRationale(requestCode: Int)
    }
}