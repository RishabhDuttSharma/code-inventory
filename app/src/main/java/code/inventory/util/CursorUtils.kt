package code.inventory.util

import android.database.Cursor

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
object CursorUtils {

    fun iterate(cursor: Cursor?, cursorIterator: CursorIterator) {
        if (cursor != null && cursor.moveToFirst()) while (cursor.moveToNext())
            cursorIterator.useCursor(cursor)
    }

    interface CursorIterator {

        fun useCursor(cursor: Cursor)
    }
}