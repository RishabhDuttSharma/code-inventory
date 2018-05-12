package code.inventory

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import code.inventory.image.BitmapProvider
import code.inventory.image.Resolution
import code.inventory.image.processor.ImageResizer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageResizer = ImageResizer(Resolution.FULL_HIGH_DEFINITION)
        imageResizer.process(BitmapProvider(""))


    }
}
