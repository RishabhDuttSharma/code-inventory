package code.inventory.image.processor

import android.graphics.Bitmap
import code.inventory.image.ImageProcessor
import code.inventory.image.Resolution

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class ImageResizer(private val width: Int?, private val height: Int?) : ImageProcessor {

    constructor(resolution: Resolution) : this(resolution.width, resolution.height)

    override fun process(bitmap: Bitmap?): Bitmap = try {

        if (width == null) throw NullPointerException("ImageResizer: width is null")

        if (height == null) throw NullPointerException("ImageResizer: height is null")

        Bitmap.createScaledBitmap(bitmap
                ?: throw NullPointerException("Image Resizing: bitmap is null"), width, height, false)

    } catch (ex: Throwable) {
        throw Exception("Error in resizing image", ex)
    }
}