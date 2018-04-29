package code.inventory.database

import android.content.ContentValues
import android.database.Cursor

/**
 * Converts the Model for storage in Database Table
 * and data in Database Table to Model.
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
interface ModelConverter<T> {

    /**
     * Converts the Cursor to Model
     *
     * @param cursor the pointer to a record
     *
     * @return instance of model
     */
    fun toModel(cursor: Cursor): T


    /**
     * Converts the Model to ContentValues
     *
     * @param model the model item
     * @return instance of ContentValues
     */
    fun toContentValues(model: T): ContentValues
}