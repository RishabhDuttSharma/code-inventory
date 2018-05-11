package code.inventory.image

/**
 * Wraps the constants used by Image Utilities
 *
 * Developer: Rishabh Dutt Sharma
 * Dated: 11-May-18.
 */

/**
 * Resolution of the Image.
 * i.e., Size/Dimensions: width x height
 */
enum class Resolution(val width: Int, val height: Int) {
    STANDARD(1024, 768),
    HIGH_DEFINITION(1280, 720),
    FULL_HIGH_DEFINITION(1920, 1080)
}

/**
 * Quality of the Image.
 * i.e., Quality retention percentage
 */
enum class Quality(val quality: Int) {
    ACTUAL(100),
    EXCELLENT(90),
    GOOD(80),
    FAIR(70),
    POOR(50),
    MISERABLE(30)
}

/**
 * Type of Crop Operation
 */
enum class Crop {
    TOP_CROP,
    CENTER_CROP,
    BOTTOM_CROP
}
