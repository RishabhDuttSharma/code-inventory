package code.inventory.util

import android.content.Context
import android.widget.Toast

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 04-May-18.
 */
object ToastUtils {

    fun showLongMessage(context: Context, message: String) = showMessage(context, message, Toast.LENGTH_LONG)

    fun showMessage(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT): Toast =
            Toast.makeText(context, message, duration).also { it.show() }

}