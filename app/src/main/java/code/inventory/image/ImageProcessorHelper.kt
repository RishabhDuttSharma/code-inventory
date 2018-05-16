package code.inventory.image

import android.graphics.Bitmap
import code.inventory.image.processor.ImageCompressor
import code.inventory.image.processor.ImageCropper
import code.inventory.image.processor.ImageResizer
import code.inventory.image.processor.ImageRotator
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 16-May-18.
 */
class ImageProcessorHelper private constructor(private val bitmap: Bitmap,
                                               private val imageProcessingQueue: LinkedBlockingQueue<ImageProcessor>) {

    /**
     * Executes
     */
    @Throws(Exception::class)
    fun executeAsync(listener: OnCompletedListener?) {

        if (listener == null) throw NullPointerException("listener cannot be null")

        thread {
            try {
                listener.onProcessed(execute())
            } catch (ex: Throwable) {
                listener.onError(ex)
            }
        }
    }

    fun execute(): Bitmap {
        var processedBitmap: Bitmap = bitmap
        imageProcessingQueue.forEach { imageProcessor ->
            processedBitmap = imageProcessor?.process(processedBitmap) ?: processedBitmap
        }
        return processedBitmap
    }

    class Builder(private val bitmap: Bitmap?) {

        private val imageProcessingQueue = LinkedBlockingQueue<ImageProcessor>()

        fun resize(width: Int, height: Int) = also { imageProcessingQueue.offer(ImageResizer(width, height)) }

        fun compress(quality: Int) = also { imageProcessingQueue.offer(ImageCompressor(quality)) }

        fun crop(x: Int, y: Int, width: Int, height: Int) = also { imageProcessingQueue.offer(ImageCropper(x, y, width, height)) }

        fun rotate(angle: Float) = also { imageProcessingQueue.offer(ImageRotator(angle)) }

        fun build() = ImageProcessorHelper(bitmap
                ?: throw Exception("Image Helper: build -> bitmap is null"), imageProcessingQueue)
    }

    interface OnCompletedListener {

        fun onProcessed(bitmap: Bitmap)

        fun onError(throwable: Throwable)
    }
}