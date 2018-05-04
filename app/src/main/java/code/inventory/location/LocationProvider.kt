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
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
class LocationProvider(private val activity: Activity,
                       private val locationSetting: LocationSetting = LocationSetting.HIGH_ACCURACY)

    : LocationCallback(), PermissionsHelper.OnRequestPermissionsCallback {


    private val mLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    private val mListener: Listener? = if (activity is Listener) activity else null

    private var mResolvingLocationError = false

    fun prepareTrackingLocation() =
            if (PermissionUtils.isPermissionsGranted(activity, LocationConstants.LOCATION_PERMISSIONS))
                checkLocationSettingsAndPrepare()
            else requestLocationPermissions()

    private fun requestLocationPermissions() = PermissionsHelper.Builder(activity)
            .addPermissions(LocationConstants.LOCATION_PERMISSIONS)
            .setOnRequestPermissionsCallback(this).build()
            .checkRationaleAndRequestPermissions(LocationConstants.REQ_LOCATION_PERMISSION)

    private fun checkLocationSettingsAndPrepare() {

        val locationRequest = prepareLocationRequest()
        val settingsRequest = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build()

        LocationServices.getSettingsClient(activity)
                .checkLocationSettings(settingsRequest)
                .addOnFailureListener { handleLocationSettingsError(it) }
                .addOnSuccessListener { startTrackingLocation() }
    }

    private fun prepareLocationRequest() = LocationRequest.create()
            .setInterval(locationSetting.updateInterval)
            .setFastestInterval(locationSetting.fastestUpdateInterval)
            .setPriority(locationSetting.priority)

    @SuppressLint("MissingPermission")
    private fun startTrackingLocation() =
            mLocationClient.requestLocationUpdates(prepareLocationRequest(), object : LocationCallback() {
                override fun onLocationResult(result: LocationResult?) {
                    if (result != null) mListener?.onLocationFetched(result.lastLocation)
                }
            }, null).also { mListener?.onStartedFetchingLocation() }

    fun stopTrackingLocation(): Task<Void> = mLocationClient.removeLocationUpdates(this)
            .also { mListener?.onStoppedFetchingLocation() }

    private fun handleLocationSettingsError(ex: Exception) {
        if ((ex as? ApiException)?.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                && (ex as? ResolvableApiException) != null) {
            if (!mResolvingLocationError) ex.startResolutionForResult(activity, LocationConstants.REQ_LOCATION_SETTINGS)
                    .also { mResolvingLocationError = true }
        } else displayError(ex.localizedMessage)
    }

    fun handleLocationSettingsResult(resultCode: Int) {

        mResolvingLocationError = false

        when (resultCode) {
            Activity.RESULT_CANCELED -> displayError(activity.getString(R.string.err_msg_location_cancelled))
                    .also { stopTrackingLocation() }
            Activity.RESULT_OK -> startTrackingLocation()
        }
    }

    fun handleRequestPermissionsResult(requestCode: Int, resultCode: Int) {

    }

    private fun displayError(message: String) = ToastUtils.showLongMessage(activity, message)

    override fun onPermissionsGranted(requestCode: Int) = prepareTrackingLocation()

    override fun showPermissionsRationale(requestCode: Int) = AlertDialog.Builder(activity)
            .setTitle(R.string.dialog_title_permission_required)
            .setMessage(R.string.dialog_message_location_rationale)
            .setPositiveButton(android.R.string.ok, { _, _ ->
                requestLocationPermissions()
            }).setNegativeButton(android.R.string.cancel, null)
            .create().show()


    interface Listener {

        /**
         * Called when LocationProvider has started fetching Location
         */
        fun onStartedFetchingLocation()

        /**
         * Called when LocationProvider receives a Location Update
         *
         * @param location the most recent available Location
         */
        fun onLocationFetched(location: Location)

        /**
         * Called when LocationProvider has stopped fetching Location
         */
        fun onStoppedFetchingLocation()

    }
}