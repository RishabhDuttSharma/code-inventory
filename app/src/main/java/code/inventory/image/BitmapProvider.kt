package code.inventory.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.annotation.DrawableRes
import code.inventory.image.loader.LocalImageLoader

/**
 * Acts as Bitmap-Provider as specified by given parameters
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
open class BitmapProvider(val bitmap: Bitmap?) {

    constructor(imagePath: String?, width: Int? = null, height: Int? = null) :
            this(LocalImageLoader(width, height).load(imagePath))

    constructor(context: Context, @DrawableRes imageResourceId: Int) :
            this(BitmapFactory.decodeResource(context.resources, imageResourceId))
}