package code.inventory.image.processor

import android.graphics.Bitmap
import android.graphics.Matrix
import code.inventory.image.ImageProcessor

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class ImageRotator(private val angle: Float = 0f) : ImageProcessor {

    override fun process(bitmap: Bitmap?): Bitmap = try {

        if (bitmap == null) throw  NullPointerException("bitmap is null")

        Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height,
                Matrix().also { it.postRotate(angle) }, false)

    } catch (ex: Throwable) {
        throw  Exception("Error in rotating image", ex)
    }
}