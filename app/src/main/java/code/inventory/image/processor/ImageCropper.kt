package code.inventory.image.processor

import android.graphics.Bitmap
import code.inventory.image.ImageProcessor

/**
 * Wraps functionality to crop a given Bitmap-Image
 *
 * @param x
 * @param y
 * @param width
 * @param height
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class ImageCropper(private val x: Int? = 0, private val y: Int? = 0,
                   private val width: Int?, private val height: Int?) : ImageProcessor {

    override fun process(bitmap: Bitmap?): Bitmap = try {

        if (bitmap == null) throw NullPointerException("bitmap is null")

        if (x == null || y == null || width == null || height == null)
            throw NullPointerException("Illegal Values: x=$x, y=$y, width=$width, height=$height")

        Bitmap.createBitmap(bitmap, x, y, width, height)

    } catch (throwable: Throwable) {
        throw  Exception("Error in cropping image", throwable)
    }
}
