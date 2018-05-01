package code.inventory.util

/**
 * @return true if all elements are value
 */
fun IntArray.containsOnly(value: Int) = any { it == value }
