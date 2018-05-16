package code.inventory.image

import android.graphics.Bitmap

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 14-May-18.
 */
interface ImageLoader<T> {

    @Throws(Exception::class)
    fun load(source: T?): Bitmap
}