package code.inventory.validation

/**
 * Represents the entity that can be Validated
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/29/2018
 */
interface Validatable<T> {

    /**
     * @return the Content for validation
     */
    fun getValidatable(): T
}