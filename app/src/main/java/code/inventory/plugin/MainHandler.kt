package code.inventory.plugin

import android.os.Handler
import android.os.Looper

/**
 * Handler to send and process messages on MAIN_THREAD
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 18-May-18.
 */
class MainHandler : Handler(Looper.getMainLooper())