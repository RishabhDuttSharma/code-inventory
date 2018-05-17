package code.inventory.image.loader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.TextUtils
import code.inventory.image.ImageLoader
import code.inventory.util.ImageUtils

/**
 * Loads Image-File from Local Storage
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 15-May-18.
 */
class LocalImageLoader(private val requiredWidth: Int? = null, private val requiredHeight: Int? = null) : ImageLoader<String> {

    override fun load(source: String?): Bitmap = try {

        if (TextUtils.isEmpty(source))
            throw Exception("Image source is either null or empty")

        if (requiredWidth == null || requiredHeight == null)
            BitmapFactory.decodeFile(source)
        else {

            if (requiredWidth <= 0)
                throw Exception("Illegal width=$requiredWidth, must be greater than zero")

            if (requiredHeight <= 0)
                throw Exception("Illegal height=$requiredHeight, must be greater than zero")

            ImageUtils.getSampledBitmap(source!!, requiredWidth, requiredHeight)
        }

    } catch (throwable: Throwable) {
        throw Exception("Error in loading image", throwable)
    }
}