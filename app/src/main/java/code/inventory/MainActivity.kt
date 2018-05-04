package code.inventory

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import code.inventory.location.LocationConstants
import code.inventory.location.LocationProvider

class MainActivity : AppCompatActivity() {

    private var locationProvider: LocationProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationProvider = LocationProvider(this)
    }

    override fun onResume() {
        super.onResume()

        locationProvider?.prepareTrackingLocation()
    }

    override fun onPause() {
        super.onPause()

        locationProvider?.stopTrackingLocation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LocationConstants.REQ_LOCATION_SETTINGS)
            locationProvider?.handleLocationSettingsResult(resultCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == LocationConstants.REQ_LOCATION_PERMISSION)
            locationProvider?.prepareTrackingLocation()
    }
}
