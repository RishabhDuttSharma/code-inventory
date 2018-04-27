package code.inventory.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import code.inventory.database.table.TableConfiguration

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
abstract class DatabaseHelper(context: Context, databaseName: String, databaseVersion: Int)
    : SQLiteOpenHelper(context, databaseName, null, databaseVersion) {

    private val tableConfigurations: List<TableConfiguration> = arrayListOf()

    override fun onCreate(db: SQLiteDatabase?) {
        for (tableConfiguration in tableConfigurations)
            db?.execSQL(QueryBuilder.createTable(tableConfiguration))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        for (tableConfiguration in tableConfigurations)
            db?.execSQL(QueryBuilder.dropTable(tableConfiguration))
        onCreate(db)
    }
}

