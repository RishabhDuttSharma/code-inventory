package code.inventory.database

/**
 * Helper class to Build Database Queries
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
object QueryBuilder {

    private const val CREATE_TABLE = "CREATE TABLE"
    private const val DROP_TABLE = "DROP TABLE"


    /**
     * @param config instance of TableConfiguration
     * @return query for Create Table
     */
    fun createTable(config: TableConfiguration): String =
            "$CREATE_TABLE ${config.getName()} ${config.getColumns()}"

    /**
     * @param config instance of TableConfiguration
     * @return query for Drop Table
     */
    fun dropTable(config: TableConfiguration): String =
            "$DROP_TABLE ${config.getName()}"
}