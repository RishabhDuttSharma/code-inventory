package code.inventory.validation

/**
 * Wraps the result generated when a validation succeeds
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/29/2018
 */
data class ValidationResult(val isValid: Boolean = true, val remarks: String = "succeeded")