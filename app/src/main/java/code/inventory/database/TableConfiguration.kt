package code.inventory.database

import code.inventory.database.Column

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
interface TableConfiguration {

    fun getTableName(): String

    fun getColumns(): Column.List
}