package code.inventory.image

import android.graphics.Bitmap

/**
 * Standard interface for processing Images (i.e., bitmaps)
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
interface ImageProcessor {

    /**
     * Processes the Image specified by BitmapProvider
     *
     * @param bitmap instance of Bitmap for processing
     *
     * @return instance of processed Bitmap
     */
    @Throws(Throwable::class)
    fun process(bitmap: Bitmap?): Bitmap
}