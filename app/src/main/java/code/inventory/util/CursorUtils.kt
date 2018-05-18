package code.inventory.util

import android.database.Cursor

/**
 * Wraps the functionality and extensions to the Cursor Class
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */

/**
 * Iterates through the Cursor and provides the subsequent cursor
 * via CursorIterator
 *
 * @param cursorIterator instance of CursorIterator to receive Cursor Callbacks
 */
fun Cursor.iterate(cursorIterator: CursorIterator) {
    if (this.moveToFirst()) do
        cursorIterator.useCursor(this)
    while (this.moveToNext())
}

interface CursorIterator {

    fun useCursor(cursor: Cursor)
}
