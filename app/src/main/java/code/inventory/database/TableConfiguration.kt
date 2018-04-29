package code.inventory.database

/**
 * Wraps information vital to Database Table
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
interface TableConfiguration {

    /**
     * @return name of the Table
     */
    fun getName(): String

    /**
     * @return List of Columns i.e., [Column.Data]
     */
    fun getColumns(): Column.List
}