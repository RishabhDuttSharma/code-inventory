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
    @Throws(Error::class)
    fun process(bitmapProvider: BitmapProvider): Bitmap


    /**
     * Wraps the error incurred while processing Image
     */
    class Error(_message: String, cause: Throwable?) : Throwable(_message, cause) {

        constructor(_message: String) : this(_message, null)

        constructor(throwable: Throwable) : this(throwable.localizedMessage, throwable)
    }
}