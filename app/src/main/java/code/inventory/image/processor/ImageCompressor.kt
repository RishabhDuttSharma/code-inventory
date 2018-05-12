package code.inventory.image.processor

import android.graphics.Bitmap
import code.inventory.image.BitmapProvider
import code.inventory.image.ImageProcessor
import code.inventory.image.Quality

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class ImageCompressor(quality: Int) : ImageProcessor {

    constructor(quality: Quality) : this(quality.quality)

    override fun process(bitmapProvider: BitmapProvider): Bitmap {
        TODO("implementation required")
    }
}