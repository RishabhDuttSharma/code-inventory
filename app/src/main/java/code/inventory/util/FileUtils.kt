package code.inventory.util

import android.graphics.Bitmap
import android.os.Environment
import java.io.File

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 28-May-18.
 */

@Throws(NullPointerException::class)
fun File.saveBitmap(bitmap: Bitmap?, path: String? = null) {


    if (bitmap == null) throw NullPointerException("bitmap is null")

    val filePath = path ?: (Environment.DIRECTORY_PICTURES + File.separator
            + createFileName("IMG", null, ".JPG"))
}

private fun File.createFileName(prefix: String?, suffix: String?, extension: String?) =
        "${prefix}_${System.currentTimeMillis()}_$suffix.$extension"
