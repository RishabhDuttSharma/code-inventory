package code.inventory.validation

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/29/2018
 */
interface Validator<T> {

    fun getValidations(): List<Validation<T>>

    @Throws(ValidationError::class)
    fun validate(validatable: Validatable<T>): List<ValidationResult> {

        val validations = getValidations()
        if (validations.isEmpty()) return emptyList()

        val alstValidationResult = ArrayList<ValidationResult>()
        for (validation in validations)
            alstValidationResult.add(validation.validate(validatable.getValidatable()))
        return alstValidationResult
    }
}