package code.inventory

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import code.inventory.location.LocationConstants
import code.inventory.location.LocationHelper
import code.inventory.plugin.AutoLog

class MainActivity : AppCompatActivity(), LocationHelper.Listener {

    override fun onLocationFetched(location: Location) {
        AutoLog.e(message = location)
    }

    private var locationHelper: LocationHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationHelper = LocationHelper(this)
    }

    override fun onResume() {
        super.onResume()

        locationHelper?.prepareTrackingLocation()
    }

    override fun onPause() {
        super.onPause()

        locationHelper?.stopTrackingLocation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LocationConstants.REQ_LOCATION_SETTINGS)
            locationHelper?.handleLocationSettingsResult(resultCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LocationConstants.REQ_LOCATION_PERMISSION)
            locationHelper?.handleRequestPermissionsResult(requestCode, grantResults)
    }
}
