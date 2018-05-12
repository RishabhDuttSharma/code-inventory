package code.inventory.unzipper

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 10-May-18.
 */
class UnzipError(val status: Int, override val message: String) : Throwable()