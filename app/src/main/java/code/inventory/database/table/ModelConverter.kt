package code.inventory.database.table

import android.content.ContentValues
import android.database.Cursor

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
interface ModelConverter<T> {

    fun toModel(cursor: Cursor): T

    fun toContentValues(model: T): ContentValues
}