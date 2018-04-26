package code.inventory.database

import android.content.ContentValues

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
interface Table<T> {

    fun query(selection: String? = null, selectionArgs: Array<String>? = null): List<T>

    fun insert(models: List<T>): Long

    fun delete(selection: String? = null, selectionArgs: Array<String>? = null): List<T>

    interface Configuration {

        fun getTableName(): String

        fun getColumns(): Column.List
    }

    interface ModelConverter<T> {

        fun toModel(contentValues: ContentValues): T

        fun toContentValues(model: T): ContentValues
    }
}