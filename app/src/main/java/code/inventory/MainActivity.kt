package code.inventory

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import code.inventory.permission.PermissionUtils
import code.inventory.permission.PermissionsHelper

class MainActivity : AppCompatActivity(), PermissionsHelper.OnRequestPermissionsCallback {

    private lateinit var permissionsHelper: PermissionsHelper

    private val REQ_LOCATION_PERMISSION: Int = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val permissions = arrayListOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (PermissionUtils.isPermissionsGranted(this, permissions).not())
            permissionsHelper = PermissionsHelper.Builder(this)
                    .addPermissions(permissions)
                    .setOnRequestPermissionsCallback(this)
                    .build().also { it.requestPermissions(REQ_LOCATION_PERMISSION) }
    }

    override fun onAllowedAllPermissions() {
        Toast.makeText(this, "onAllowedAllPermissions", Toast.LENGTH_SHORT).show()
    }

    override fun showPermissionsRationale(requestCode: Int) {
        Toast.makeText(this, "showPermissionsRationale", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQ_LOCATION_PERMISSION)
            permissionsHelper.onRequestPermissionsResult(requestCode, grantResults)
    }
}
