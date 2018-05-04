package code.inventory

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/30/2018.
 */
enum class ModulesCoverage(val completed: Boolean) {

    NETWORK(true),
    DATABASE(true),
    AUTO_LOG(true),
    VALIDATION(true),   // 30-04-2018
    LOCATION(true),     // 04-05-2018
    UNZIPPER(false),
    APP_UPDATER(false),
    SMS_READER(false),
    PERMISSION_MANAGER(true),   // 02-05-2018
    ENCRYPTION_MANAGER(false),
}