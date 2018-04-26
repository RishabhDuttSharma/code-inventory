package code.inventory.database

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
object QueryBuilder {

    private const val CREATE_TABLE = "CREATE TABLE"
    private const val DROP_TABLE = "DROP TABLE"

    fun createTable(config: Table.Configuration): String =
            "$CREATE_TABLE ${config.getTableName()} ${config.getColumns().prepareColumnsInfo()}"

    fun dropTable(config: Table.Configuration): String =
            "$DROP_TABLE ${config.getTableName()}"
}