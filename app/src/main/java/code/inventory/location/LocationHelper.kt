package code.inventory.location

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.support.v7.app.AlertDialog
import code.inventory.R
import code.inventory.permission.PermissionUtils
import code.inventory.permission.PermissionsHelper
import code.inventory.util.ToastUtils
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import java.lang.Exception

/**
 * Wraps the Functionality to provide Location Services
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
class LocationHelper(private val activity: Activity,
                     private val locationSetting: LocationSetting = LocationSetting.HIGH_ACCURACY)

    : LocationCallback(), PermissionsHelper.OnRequestPermissionsCallback {


    private val mLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    private val mListener: Listener? = if (activity is Listener) activity else null

    private var mResolvingLocationError = false
    private var mShowingPermissionsRationale = false
    private var mLocationSettingRequestCancelled = false

    /**
     * Performs checks and starts requesting Location updates
     */
    fun prepareTrackingLocation() =
            if (PermissionUtils.isPermissionsGranted(activity, LocationConstants.LOCATION_PERMISSIONS))
                checkLocationSettingsAndPrepare()
            else requestLocationPermissions()

    /**
     * Checks permissions required for accessing User Location.
     *
     * @param checkRationale checks rationale if true, directly requests permissions otherwise
     */
    private fun requestLocationPermissions(checkRationale: Boolean = true) {
        buildPermissionsHelper().also {
            if (checkRationale)
                it.checkRationaleAndRequestPermissions(LocationConstants.REQ_LOCATION_PERMISSION)
            else it.requestPermissions(LocationConstants.REQ_LOCATION_PERMISSION)
        }
    }

    /**
     * @return a new instance of PermissionsHelper with required Permissions
     */
    private fun buildPermissionsHelper() = PermissionsHelper.Builder(activity)
            .addPermissions(LocationConstants.LOCATION_PERMISSIONS)
            .setOnRequestPermissionsCallback(this).build()

    /**
     * Show enable Location dialog, if Location is not enabled.
     * Callbacks are received on corresponding events.
     *
     * Starts Tracking Location if Location is enabled
     */
    private fun checkLocationSettingsAndPrepare() {

        val locationRequest = prepareLocationRequest()
        val settingsRequest = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build()

        LocationServices.getSettingsClient(activity)
                .checkLocationSettings(settingsRequest)
                .addOnFailureListener { handleLocationSettingsError(it) }
                .addOnSuccessListener { startTrackingLocation() }
    }


    /**
     * @return a new instance of LocationRequest
     */
    private fun prepareLocationRequest() = LocationRequest.create()
            .setInterval(locationSetting.updateInterval)
            .setFastestInterval(locationSetting.fastestUpdateInterval)
            .setPriority(locationSetting.priority)

    /**
     * Starts requesting Location Updates as told by LocationRequest
     */
    @SuppressLint("MissingPermission")
    private fun startTrackingLocation() = mLocationClient.requestLocationUpdates(prepareLocationRequest(), this, null)
            .also { mListener?.onStartedFetchingLocation() }

    override fun onLocationResult(result: LocationResult?) {
        if (result != null) mListener?.onLocationFetched(result.lastLocation)
    }

    /**
     * Stops requesting Location Updates on this LocationCallback
     */
    fun stopTrackingLocation(): Task<Void> = mLocationClient.removeLocationUpdates(this)
            .also { mListener?.onStoppedFetchingLocation() }

    /**
     * Handles the Exception thrown during the User Consent for enabling the Location
     */
    private fun handleLocationSettingsError(ex: Exception) {
        if ((ex as? ApiException)?.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                && (ex as? ResolvableApiException) != null) {
            if (!mResolvingLocationError && !mLocationSettingRequestCancelled)
                ex.startResolutionForResult(activity, LocationConstants.REQ_LOCATION_SETTINGS)
                        .also { mResolvingLocationError = true }
        } else displayError(ex.localizedMessage)
    }


    /**
     * Starts Tracking Location if user allowed Location Consent, Stops Location Tracking otherwise
     */
    fun handleLocationSettingsResult(resultCode: Int) {

        // Reset Location Settings flag
        mResolvingLocationError = false

        if (resultCode == Activity.RESULT_CANCELED) displayError(activity.getString(R.string.err_msg_location_cancelled)).also {
            // Set Flag
            mLocationSettingRequestCancelled = true
            // Stop Location Updates
            stopTrackingLocation()

        } else if (resultCode == Activity.RESULT_OK) startTrackingLocation()
    }

    /**
     * Handles the result from requesting Location Permissions
     */
    fun handleRequestPermissionsResult(requestCode: Int, grantResults: IntArray) =
            buildPermissionsHelper().handleRequestPermissionsResult(requestCode, grantResults)

    /**
     * Shows error Toast message
     */
    private fun displayError(message: String) = ToastUtils.showLongMessage(activity, message)


    override fun onPermissionsGranted(requestCode: Int) = prepareTrackingLocation()

    override fun showPermissionsRationale(requestCode: Int) {
        if (!mShowingPermissionsRationale) AlertDialog.Builder(activity)
                .setTitle(R.string.dialog_title_permission_required)
                .setMessage(R.string.dialog_message_location_rationale)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    requestLocationPermissions(false)
                }).setNegativeButton(android.R.string.cancel, null)
                .setOnDismissListener { mShowingPermissionsRationale = false }
                .create().show().also { mShowingPermissionsRationale = true }
    }

    interface Listener {

        /**
         * Called when LocationHelper has started fetching Location
         */
        fun onStartedFetchingLocation() {}

        /**
         * Called when LocationHelper receives a Location Update
         *
         * @param location the most recent available Location
         */
        fun onLocationFetched(location: Location)

        /**
         * Called when LocationHelper has stopped fetching Location
         */
        fun onStoppedFetchingLocation() {}
    }
}