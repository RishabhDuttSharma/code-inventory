package code.inventory.unzipper

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 10-May-18.
 */
interface Unzipper<T> {

    @Throws(UnzipError::class)
    fun <T> unzip(source: T): UnzipResult
}