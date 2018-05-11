package code.inventory.image

import android.graphics.Bitmap

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
interface ImageProcessor {

    fun process(bitmapProvider: BitmapProvider): Bitmap
}