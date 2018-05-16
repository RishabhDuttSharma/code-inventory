package code.inventory.image.processor

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import code.inventory.image.ImageProcessor
import code.inventory.image.Quality
import java.io.ByteArrayOutputStream

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class ImageCompressor(val quality: Int? = 100) : ImageProcessor {

    constructor(quality: Quality) : this(quality.quality)

    override fun process(bitmap: Bitmap?): Bitmap = try {

        if (bitmap == null) throw Exception("ImageCompressor: bitmap is null")

        if (quality == null) throw Exception("ImageCompressor: quality is null")

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val byteArray = outputStream.toByteArray()
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

    } catch (ex: Throwable) {
        throw Exception("Error in compressing image", ex)
    }
}