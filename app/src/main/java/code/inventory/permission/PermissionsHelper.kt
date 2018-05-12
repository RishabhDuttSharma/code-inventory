package code.inventory.permission

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import code.inventory.util.containsOnly

/**
 * Wraps functionality to handle permissions
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 01-May-18.
 */
class PermissionsHelper private constructor(private val activity: Activity) {

    private val permissions: ArrayList<String> = ArrayList()
    private var callback: OnRequestPermissionsCallback? = null

    /**
     * Builds an instance of PermissionHelper
     */
    class Builder(activity: Activity) {

        private val permissionsHelper = PermissionsHelper(activity)

        /**
         * Adds permission
         *
         * @param permission
         */
        fun addPermission(permission: String) = this
                .also { permissionsHelper.permissions.add(permission) }

        /**
         * Adds permissions
         *
         * @param List of permissions
         */
        fun addPermissions(permissions: List<String>) = this
                .also { permissionsHelper.permissions.addAll(permissions) }

        /**
         * Setter for callback
         *
         * @param callback instance of callback to be used to receive events
         * from Requesting Permissions
         */
        fun setOnRequestPermissionsCallback(callback: OnRequestPermissionsCallback) = this
                .also { permissionsHelper.callback = callback }

        /**
         * @return an instance of PermissionsHelper
         */
        fun build() = permissionsHelper
    }


    /**
     * Calls OnRequestPermissionsCallback.showPermissionsRationale(requestCode: Int)
     * if a rationale is required to be displayed to end-user. Otherwise, directly asks
     * the permissions
     *
     * @param requestCode code differentiate between multiple requests
     */
    fun checkRationaleAndRequestPermissions(requestCode: Int) {
        if (PermissionUtils.hasRationalePermissions(activity, permissions))
            callback?.showPermissionsRationale(requestCode)
        else requestPermissions(requestCode)
    }

    /**
     * Requests permissions added by User while building instance of PermissionsHelper
     *
     * @param requestCode code differentiate between multiple requests
     */
    fun requestPermissions(requestCode: Int) =
            ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), requestCode)

    /**
     * Handles the result from requesting permissions
     *
     * @param requestCode
     * @param grantResults
     */
    fun handleRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (grantResults.containsOnly(PackageManager.PERMISSION_GRANTED))
            callback?.onPermissionsGranted(requestCode)
        else callback?.showPermissionsRationale(requestCode)
    }

    /**
     * Callback interface to be triggered when the end-user is done with
     * Permissions Consent
     */
    interface OnRequestPermissionsCallback {

        /**
         * Called when all permissions are granted
         */
        fun onPermissionsGranted(requestCode: Int)

        /**
         * Called when a Permissions Rationale is required for End-User
         */
        fun showPermissionsRationale(requestCode: Int)
    }
}