package code.inventory.image.processor

import android.graphics.Bitmap
import code.inventory.image.BitmapProvider
import code.inventory.image.ImageProcessor
import code.inventory.image.Resolution

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class ImageResizer(private val width: Int, private val height: Int) : ImageProcessor {

    constructor(resolution: Resolution) : this(resolution.width, resolution.height)

    override fun process(bitmapProvider: BitmapProvider): Bitmap = try {
        Bitmap.createScaledBitmap(bitmapProvider.bitmap, width, height, false)
    } catch (ex: Throwable) {
        throw ImageProcessor.Error(ex.localizedMessage)
    }
}