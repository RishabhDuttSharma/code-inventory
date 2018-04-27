package code.inventory.database

import code.inventory.database.table.DatabaseTable

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
interface Database {

    fun getConfiguration(): Configuration

    interface Configuration {

        fun getName(): String

        fun getVersion(): Int

        fun getTables(): List<DatabaseTable.Configuration>
    }
}