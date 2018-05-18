package code.inventory.util

import java.util.*

/**
 * @return true, if all elements are value, false otherwise
 */
fun IntArray.containsOnly(value: Int) = any { it == value }

/**
 * @return true, if collection is null or empty, false otherwise
 */
fun <E> Collections.isNullOrEmpty(collection: Collection<E>?) = collection == null || collection.isEmpty()


