package code.inventory

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import code.inventory.file.FileCreator
import code.inventory.permission.PermissionsHelper
import code.inventory.util.ToastUtils

class MainActivity : AppCompatActivity(), PermissionsHelper.OnRequestPermissionsCallback {

    override fun onPermissionsGranted(requestCode: Int) {

        if (requestCode == 500) {
            FileCreator.Builder().fileName("CREATOR")
                    .suffix("DEMO")
                    .prefix("FILE")
                    .extension("EXT")
                    .directory(getExternalFilesDir(null)).build()
                    .create()
        }
    }

    override fun showPermissionsRationale(requestCode: Int) {
        ToastUtils.showMessage(this, "Permission Storage is required!")
    }

    private lateinit var permissionsHelper: PermissionsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionsHelper = PermissionsHelper.Builder(this)
                .addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setOnRequestPermissionsCallback(this).build()
        permissionsHelper.checkRationaleAndRequestPermissions(500)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 500) permissionsHelper.handleRequestPermissionsResult(requestCode, grantResults)
    }
}
