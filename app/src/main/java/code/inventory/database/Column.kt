package code.inventory.database

import android.text.TextUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * Wraps Column properties.
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
object Column {

    /**
     * Constraints, that can be applied to the Database-Column
     */
    enum class Constraint(val value: String) {
        AUTO_INCREMENT("AUTO INCREMENT"),
        PRIMARY_KEY("PRIMARY KEY")
    }


    /**
     * DataTypes supported by SQLite for a Database-Column
     */
    enum class DataType(val value: String) {
        NULL("NULL"),
        INTEGER("INTEGER"),
        REAL("REAL"),
        TEXT("TEXT"),
        BLOB("BLOB")
    }


    /**
     * Represents Column-Information in Database Table
     */
    data class Data(val columnName: String,
                    private val columnDataType: String,
                    private val constraints: String) {
        override fun toString() = "$columnName $columnDataType $constraints"
    }


    /**
     * Linked List representation of Columns in DatabaseTables
     */
    class List : LinkedList<Data>() {

        /**
         * Adds/appends new Column
         *
         * @param columnName
         * @param columnDataType
         * @param constraints (optional)
         *
         * @return chaining reference
         */
        fun newColumn(columnName: String, columnDataType: DataType, vararg constraints: Constraint) =
                newColumn(columnName, columnDataType.value, TextUtils.join(" ", constraints))

        /**
         * Adds/appends new Column
         *
         * @param columnName
         * @param columnDataType
         * @param constraints
         *
         * @return chaining reference
         */
        fun newColumn(columnName: String, columnDataType: String, constraints: String) = also {
            add(Data(columnName, columnDataType, constraints))
        }

        /**
         * @return arrayOf ColumnNames that can be used as Projection in Querying Database
         */
        fun asProjection(): Array<String> = flatMapTo(ArrayList(), { (columnName) ->
            kotlin.collections.listOf(columnName)
        }).toArray(arrayOfNulls<String>(size))


        /**
         * Prepares Column-Information that can be used to create Tables
         */
        override fun toString(): String = TextUtils.join(", ", this)
    }
}