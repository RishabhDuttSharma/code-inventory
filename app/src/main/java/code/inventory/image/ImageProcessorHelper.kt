package code.inventory.image

import android.graphics.Bitmap
import code.inventory.image.processor.ImageCompressor
import code.inventory.image.processor.ImageCropper
import code.inventory.image.processor.ImageResizer
import code.inventory.image.processor.ImageRotator
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/**
 * Helper class to perform multiple ImageProcessing on an Image-Bitmap
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 16-May-18.
 */
class ImageProcessorHelper private constructor(private val bitmap: Bitmap,
                                               private val imageProcessingQueue: LinkedBlockingQueue<ImageProcessor>) {

    /**
     * Executes the Image Processing Queue asynchronously
     *
     * @param listener an instance of listener that would receive processing results
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

    /**
     * Executes the ImageProcessors on same thread
     *
     * @return processed image-bitmap
     */
    fun execute(): Bitmap {
        var processedBitmap: Bitmap = bitmap
        imageProcessingQueue.forEach { imageProcessor ->
            processedBitmap = imageProcessor?.process(processedBitmap) ?: processedBitmap
        }
        return processedBitmap
    }

    /**
     * Builds an instance of ImageProcessorHelper
     */
    class Builder(private val bitmap: Bitmap?) {

        private val imageProcessingQueue = LinkedBlockingQueue<ImageProcessor>()

        /**
         * Resize the Bitmap to given width and height
         *
         * @param width target width of bitmap
         * @param height target height of the bitmap
         */
        fun resize(width: Int, height: Int) = also { imageProcessingQueue.offer(ImageResizer(width, height)) }

        /**
         * Downgrades the quality of the Bitmap to given quality value
         *
         * @param quality target quality of bitmap
         */
        fun compress(quality: Int) = also { imageProcessingQueue.offer(ImageCompressor(quality)) }

        /**
         * Crops the bitmap with given parameters
         *
         * @param x column pixel of start of crop
         * @param y row pixel of start of crop
         * @param width width of the crop
         * @param height height of the crop
         */
        fun crop(x: Int, y: Int, width: Int, height: Int) = also { imageProcessingQueue.offer(ImageCropper(x, y, width, height)) }

        /**
         * Rotates the bitmap to given angle
         *
         * @param angle the angle at the which the bitmap should be rotated
         */
        fun rotate(angle: Float) = also { imageProcessingQueue.offer(ImageRotator(angle)) }

        /**
         * @return an instance of ImageProcessorHelper as specified by Builder Parameters
         */
        fun build() = ImageProcessorHelper(bitmap
                ?: throw Exception("Image Helper: build -> bitmap is null"), imageProcessingQueue)
    }

    /**
     * Interface callback definition, invoked when the ImageProcessorHelper has
     * completed processing the Bitmap.
     */
    interface OnCompletedListener {

        /**
         * Called when the Bitmap has been successfully processed
         * through given queue of ImageProcessors
         *
         * @param bitmap the final processed bitmap
         */
        fun onProcessed(bitmap: Bitmap)

        /**
         * Called when an Error is occurred while processing the Bitmap
         */
        fun onError(throwable: Throwable)
    }
}