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
     * @param bitmapProvider provider for Bitmap
     *
     * @return instance of processed Bitmap
     */
    fun process(bitmapProvider: BitmapProvider): Bitmap
}