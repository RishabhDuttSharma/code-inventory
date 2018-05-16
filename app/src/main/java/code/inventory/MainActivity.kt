package code.inventory

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import code.inventory.image.ImageProcessorHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resourceBitmap = BitmapFactory.decodeResource(resources, R.drawable.screenshot)

        ImageProcessorHelper.Builder(resourceBitmap)
                .resize(100, 100)
                .crop(25, 25, 75, 75)
                .compress(50).rotate(45f).build()
                .executeAsync(object : ImageProcessorHelper.OnCompletedListener {

                    override fun onProcessed(bitmap: Bitmap) {
                        tv.setImageBitmap(bitmap)
                    }

                    override fun onError(throwable: Throwable) {
                        TODO("implementation required")
                    }
                })
    }
}
