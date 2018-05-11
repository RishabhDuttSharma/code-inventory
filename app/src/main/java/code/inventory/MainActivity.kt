package code.inventory

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import code.inventory.image.processor.ImageResizer
import code.inventory.image.Resolution

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resize = ImageResizer("").resize(Resolution.FULL_HIGH_DEFINITION)
    }
}
