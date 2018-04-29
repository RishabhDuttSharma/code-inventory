package code.inventory.validation

/**
 * Wraps the functionality for the process of Validation.<p/>
 * Encapsulates the list of Validations and verifies them against a Validatable.
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/29/2018
 */
interface Validator<T> {

    /**
     * @return the List of Validations required for Validation by Validator
     */
    fun getValidations(): List<Validation<T>>

    /**
     * Validates given validatable against the List of validations defined by [getValidations()]
     *
     * @param validatable
     */
    @Throws(ValidationError::class)
    fun validate(validatable: Validatable<T>): List<ValidationResult> = getValidations().map {
        it.validate(validatable.getValidatable())
    }

}