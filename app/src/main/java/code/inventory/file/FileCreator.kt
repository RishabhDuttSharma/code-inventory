package code.inventory.file

import android.os.Environment
import java.io.File
import java.io.FileOutputStream

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 28-May-18.
 */
class FileCreator(private val file: File?) {

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

            var completeFileName = fileName ?: System.currentTimeMillis().toString()

            if (prefix != null)
                completeFileName = "${prefix}_$completeFileName"

            if (suffix != null)
                completeFileName += "_$suffix"

            if (extension != null)
                completeFileName += ".$extension"

            directory = directory ?: Environment.getExternalStorageDirectory().absolutePath

            return FileCreator(File(directory, completeFileName))
        }
    }


    /**
     * Creates a new File, if either file doesn't exists or overwrite flag is true
     *
     * @param overwrite overwrites the given file with a new empty file, if exists
     *
     * @throws NullPointerException, if either filePath is null or overwrite flag is null
     */
    @Throws(NullPointerException::class)
    fun create(overwrite: Boolean? = true): File {

        if (file == null) throw NullPointerException("file path is null")

        if (overwrite == null) throw NullPointerException("overwrite flag is null")

        if (file.exists() && overwrite) file.delete()

        return file.also { it.createNewFile() }
    }

    /**
     * Writes given data (byteArray) to file
     */
    fun write(byteArray: ByteArray) {


        val outStream = FileOutputStream(file)


    }
}