package code.inventory.image.loader

import android.graphics.Bitmap
import android.text.TextUtils
import code.inventory.image.ImageLoader
import code.inventory.util.ImageUtils

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 15-May-18.
 */
class LocalImageLoader(private val requiredWidth: Int = 100, private val requiredHeight: Int = 100) : ImageLoader<String> {

    override fun load(source: String?): Bitmap {

        if (requiredWidth <= 0)
            throw Throwable("requiredWidth must be greater than zero")

        if (requiredHeight <= 0)
            throw Throwable("requiredHeight must be greater than zero")

        if (TextUtils.isEmpty(source))
            throw Throwable("source is null")

        return ImageUtils.getSampledBitmap(source!!, requiredWidth, requiredHeight)
    }
}