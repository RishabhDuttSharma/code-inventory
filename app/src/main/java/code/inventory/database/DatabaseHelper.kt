package code.inventory.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
class DatabaseHelper(context: Context, private val configuration: DatabaseConfiguration)
    : SQLiteOpenHelper(context, configuration.getName(), null, configuration.getVersion()) {

    override fun onCreate(db: SQLiteDatabase?) {
        for (tableConfiguration in configuration.getTables())
            db?.execSQL(QueryBuilder.createTable(tableConfiguration))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        for (tableConfiguration in configuration.getTables())
            db?.execSQL(QueryBuilder.dropTable(tableConfiguration))
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}

