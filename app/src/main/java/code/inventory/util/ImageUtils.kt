package code.inventory.util

import android.graphics.BitmapFactory
import android.media.ExifInterface

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 14-May-18.
 */
object ImageUtils {

    @Throws(Throwable::class)
    fun getImageOrientation(imagePath: String?): Int {

        val exifInterface = ExifInterface(imagePath)
        val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)

        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }
    }

    fun calculateInSampleSize(options: BitmapFactory.Options, requiredWidth: Int, requiredHeight: Int): Int {

        val bitmapHeight = options.outHeight
        val bitmapWidth = options.outWidth

        var inSampleSize = 1

        if (bitmapHeight > requiredHeight || bitmapWidth > requiredWidth) {


            val halfBitmapHeight = bitmapHeight / 2
            val halfBitmapWidth = bitmapWidth / 2

            // Calculates the largest inSampleSize value that is POWER OF 2 and keeps
            // both bitmapWidth and bitmapHeight larger than the requiredWidth and
            // requiredHeight.

            while ((halfBitmapHeight / inSampleSize) > requiredHeight
                    && (halfBitmapWidth / inSampleSize) > requiredWidth)
                inSampleSize *= 2   // inSampleSize would always be in POWER OF 2

            // NOTE: A "POWER OF 2" is calculated because the decoder uses a final value
            // by rounding down to nearest "POWER OF 2", as per inSampleSize documentation.
        }

        return inSampleSize
    }
}