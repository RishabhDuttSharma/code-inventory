package code.inventory.image.processor

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import code.inventory.image.BitmapProvider
import code.inventory.image.ImageProcessor
import code.inventory.image.Quality
import java.io.ByteArrayOutputStream

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class ImageCompressor(val quality: Int = 100) : ImageProcessor {

    constructor(quality: Quality) : this(quality.quality)

    override fun process(bitmapProvider: BitmapProvider): Bitmap = try {
        val outputStream = ByteArrayOutputStream()
        bitmapProvider.bitmap?.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val byteArray = outputStream.toByteArray()
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    } catch (ex: Throwable) {
        throw ImageProcessor.Error("Image Compression Error", ex)
    }
}