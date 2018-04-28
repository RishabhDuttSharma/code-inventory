package code.inventory.database

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
object QueryBuilder {

    private const val CREATE_TABLE = "CREATE TABLE"
    private const val DROP_TABLE = "DROP TABLE"

    fun createTable(config: TableConfiguration): String =
            "$CREATE_TABLE ${config.getTableName()} ${config.getColumns()}"

    fun dropTable(config: TableConfiguration): String =
            "$DROP_TABLE ${config.getTableName()}"
}