package code.inventory.validation

/**
 * Represents the entity that validates a given Input
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/29/2018
 */
interface Validation<T> {

    /**
     * Validates given input
     *
     * @param input the input to be validated
     *
     * @return ValidationResult, if validation succeeds
     * @throws ValidationError, if validation fails
     */
    @Throws(ValidationError::class)
    fun validate(input: T): ValidationResult
}