package code.inventory.database

import android.text.TextUtils
import java.util.*

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
object Column {

    enum class Constraint(val value: String) {

        AUTO_INCREMENT("AUTO INCREMENT"),
        PRIMARY_KEY("PRIMARY KEY")
    }

    enum class DataType(val value: String) {
        NULL("NULL"),
        INTEGER("INTEGER"),
        REAL("REAL"),
        TEXT("TEXT"),
        BLOB("BLOB")
    }

    data class Data(private val columnName: String,
                    private val columnDataType: String,
                    private val constraints: String) {
        override fun toString() = "$columnName $columnDataType $constraints"
    }


    class List : LinkedList<Data>() {

        fun newColumn(columnName: String, columnDataType: DataType, vararg constraints: Constraint) =
                newColumn(columnName, columnDataType.value, TextUtils.join(" ", constraints))

        fun newColumn(columnName: String, columnDataType: String, constraints: String) = also {
            add(Data(columnName, columnDataType, constraints))
        }

        fun prepareColumnsInfo(): String = TextUtils.join(", ", this)
    }
}