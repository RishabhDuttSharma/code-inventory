package code.inventory.unzipper.impl

import code.inventory.unzipper.UnzipResult
import code.inventory.unzipper.Unzipper
import java.net.URL

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */
class WebUnzipper : Unzipper<URL> {

    override fun <T> unzip(source: T): UnzipResult = UnzipResult()
}