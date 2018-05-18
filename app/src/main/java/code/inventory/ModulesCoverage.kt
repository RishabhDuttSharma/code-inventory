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
    PERMISSION_MANAGER(true),   // 02-05-2018
    IMAGE(true), // 18-05-2018
    FILE_UTILS(false),
    APP_UPDATER(false),
    SMS_READER(false),
    SECURE_SHARED_PREFERENCES(false),
    UNZIPPER(false),
    ENCRYPTION_MANAGER(false),
}