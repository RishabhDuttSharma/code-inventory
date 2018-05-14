package code.inventory.image.processor

import android.graphics.Bitmap
import android.graphics.Matrix
import code.inventory.image.BitmapProvider
import code.inventory.image.ImageProcessor

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class ImageRotator(val angle: Float = 0f) : ImageProcessor {

    override fun process(bitmapProvider: BitmapProvider): Bitmap {

        val bitmap = bitmapProvider.bitmap
                ?: throw  ImageProcessor.Error("bitmap is null", NullPointerException())

        return try {
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height,
                    Matrix().also { it.postRotate(angle) }, true)
        } catch (ex: Throwable) {
            throw  ImageProcessor.Error("Error in rotating bitmap", ex)
        }
    }
}