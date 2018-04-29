package code.inventory.database

/**
 * Wraps information vital to Database
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
interface DatabaseConfiguration {

    /**
     * @return name of the Database
     */
    fun getName(): String

    /**
     * @return version of the Database
     */
    fun getVersion(): Int

    /**
     * @return List of Tables i.e., [TableConfiguration]
     */
    fun getTables(): List<TableConfiguration>
}
