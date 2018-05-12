package code.inventory.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.annotation.DrawableRes

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
open class BitmapProvider(val bitmap: Bitmap?) {

    constructor(imagePath: String?) : this(BitmapFactory.decodeFile(imagePath))

    constructor(context: Context, @DrawableRes imageResourceId: Int) :
            this(BitmapFactory.decodeResource(context.resources, imageResourceId))
}