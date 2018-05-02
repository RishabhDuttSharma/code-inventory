package code.inventory

/**
 * Developer: Rishabh Dutt Sharma
 * Dated: 4/30/2018.
 */
enum class ModulesCoverage(val completed: Boolean) {

    NETWORK(true),
    DATABASE(true),
    AUTO_LOG(true),
    VALIDATION(true), // 30-Apr-2018
    LOCATION(false),
    UNZIPPER(false),
    APP_UPDATER(false),
    SMS_READER(false),
    PERMISSION_MANAGER(true),
    ENCRYPTION_MANAGER(false),
}