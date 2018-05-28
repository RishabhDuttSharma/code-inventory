package code.inventory.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import code.inventory.util.CursorIterator
import code.inventory.util.iterate

/**
 * Represents Database Table
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
abstract class DatabaseTable<T>(private val databaseHelper: DatabaseHelper) : TableOperations<T> {

    /**
     * @return the Configuration for this Table
     */
    abstract fun getConfiguration(): TableConfiguration

    /**
     * @return the Converter to parse the Model
     */
    abstract fun getModelConverter(): ModelConverter<T>


    override fun query(selection: String?, selectionArgs: Array<String>?): List<T> {

        val resultList = ArrayList<T>()
        val database = databaseHelper.readableDatabase

        val configuration = getConfiguration()
        val cursor = database.query(configuration.getName(), configuration.getColumns().asProjection(),
                selection, selectionArgs, null, null, null)

        cursor?.iterate(object : CursorIterator {
            override fun useCursor(cursor: Cursor) {
                resultList.add(getModelConverter().toModel(cursor))
            }
        })
        cursor?.close()

        return resultList
    }

    override fun insert(models: List<T>?): Int {

        if (models == null || models.isEmpty()) return 0

        val database = databaseHelper.writableDatabase.also { it.beginTransaction() }
        val tableName = getConfiguration().getName()

        return try {
            for (model in models) database.insertWithOnConflict(tableName, null,
                    getModelConverter().toContentValues(model), SQLiteDatabase.CONFLICT_REPLACE)
            1
        } catch (ex: Exception) {
            ex.printStackTrace()
            -1
        } finally {
            database.endTransaction()
        }
    }

    override fun delete(selection: String?, selectionArgs: Array<String>?): Int {
        return databaseHelper.writableDatabase.delete(getConfiguration().getName(), selection, selectionArgs)
    }
}