package code.inventory.database

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
interface DatabaseConfiguration {

    fun getName(): String

    fun getVersion(): Int

    fun getTables(): List<TableConfiguration>
}
