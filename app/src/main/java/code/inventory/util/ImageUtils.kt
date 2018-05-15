package code.inventory.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 14-May-18.
 */
object ImageUtils {

    /**
     * @return the Orientation of the Bitmap in the ImageFile
     */
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

    /**
     * Calculates the BITMAP SAMPLING SIZE in POWER OF 2
     *
     * @return nearest Sample Size in Power of 2
     */
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

    /**
     * @param imagePath the local path of the ImageFile
     * @param requiredWidth the desired/requested width of output Image
     * @param requiredHeight the desired/requested height of output Image
     *
     * @return a smaller (sampled) version of the Bitmap specified by ImagePath
     */
    fun getSampledBitmap(imagePath: String, requiredWidth: Int, requiredHeight: Int): Bitmap =

            BitmapFactory.decodeFile(imagePath, BitmapFactory.Options()
                    .also {
                        it.inJustDecodeBounds = true

                        BitmapFactory.decodeFile(imagePath, it)

                        it.inSampleSize = calculateInSampleSize(it, requiredWidth, requiredHeight)
                        it.inJustDecodeBounds = false
                    }
            )
}