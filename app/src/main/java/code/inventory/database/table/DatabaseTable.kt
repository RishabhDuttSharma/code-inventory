package code.inventory.database.table

import android.database.sqlite.SQLiteDatabase
import code.inventory.database.DatabaseHelper

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
abstract class DatabaseTable<T>(private val databaseHelper: DatabaseHelper) : TableOperations<T> {

    abstract fun getConfiguration(): TableConfiguration

    abstract fun getModelConverter(): ModelConverter<T>

    override fun query(selection: String?, selectionArgs: Array<String>?): List<T> {

        val resultList = ArrayList<T>()
        val database = databaseHelper.readableDatabase

        val configuration = getConfiguration()
        val cursor = database.query(configuration.getTableName(), configuration.getColumns().asProjection(), selection, selectionArgs, null, null, null)
        if (cursor != null && cursor.moveToFirst()) while (cursor.moveToNext())
            resultList.add(getModelConverter().toModel(cursor))
        cursor.close()

        return resultList
    }

    override fun insert(models: List<T>?): Int {

        if (models == null || models.isEmpty()) return 0

        val database = databaseHelper.writableDatabase.also { it.beginTransaction() }
        val tableName = getConfiguration().getTableName()

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
        return databaseHelper.writableDatabase.delete(getConfiguration().getTableName(), selection, selectionArgs)
    }
}