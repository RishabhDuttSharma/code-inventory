package code.inventory.database

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/27/2018.
 */
interface TableOperations<T> {

    /**
     * @param selection condition with '?'
     * @param selectionArgs arguments to replace '?'
     *
     * @return the List of models
     *
     */
    fun query(selection: String? = null, selectionArgs: Array<String>? = null): List<T>


    /**
     *
     * @param models the list of Models
     *
     * @return 0 if NO RECORDS INSERTED,
     *         1 if ALL RECORDS INSERTED,
     *        -1 if EXCEPTION OCCURRED WHILE INSERTION
     */
    fun insert(models: List<T>?): Int


    /**
     * @param selection condition with '?'
     * @param selectionArgs arguments to replace '?'
     *
     * @return number of records deleted
     */
    fun delete(selection: String? = null, selectionArgs: Array<String>? = null): Int
}