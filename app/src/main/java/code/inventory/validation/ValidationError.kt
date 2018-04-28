package code.inventory.validation

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/29/2018
 */
data class ValidationError(val errorType: Int, val errorMessage: String): Throwable()