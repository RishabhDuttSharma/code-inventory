package code.inventory.util

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/26/2018.
 */
object ValueUtils {

    fun <V> getValue(value: V?, defaultVal: V) = value ?: defaultVal
}