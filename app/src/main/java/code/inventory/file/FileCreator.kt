package code.inventory.file

import android.os.Environment
import java.io.File

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 28-May-18.
 */
class FileCreator private constructor(private val filePath: String?) {

    class Builder {

        private var fileName: String? = null
        private var suffix: String? = null
        private var prefix: String? = null
        private var extension: String? = null
        private var directory: String? = null

        fun fileName(name: String?) = also { this.fileName = name }

        fun prefix(prefix: String?) = also { this.prefix = prefix }

        fun suffix(suffix: String?) = also { this.suffix = suffix }

        fun extension(extension: String?) = also { this.extension = extension }

        fun directory(directory: String?) = also { this.directory = directory }

        fun directory(directory: File?) = also { this.directory = directory?.absolutePath }

        fun build(): FileCreator {

            var filePath = fileName ?: System.currentTimeMillis().toString()

            if (prefix != null)
                filePath = "${prefix}_$filePath"

            if (suffix != null)
                filePath += "_$suffix"

            if (extension != null)
                filePath += ".$extension"

            directory = directory ?: Environment.getExternalStorageDirectory().absolutePath

            return FileCreator("$directory${File.separator}$filePath")
        }
    }

    @Throws(NullPointerException::class)
    fun create(overwrite: Boolean? = true): File {

        if (filePath == null) throw NullPointerException("file path is null")

        if (overwrite == null) throw NullPointerException("overwrite flag is null")

        val file = File(filePath)

        if (file.exists() && overwrite) file.delete()

        return file.also { it.createNewFile() }
    }
}