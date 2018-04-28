package code.inventory.validation

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/29/2018
 */
interface Validation <T>{

    @Throws(ValidationError::class)
    fun validate(input: T): ValidationResult
}