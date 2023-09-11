package ru.trforcex.uni.cg.primitives.util

/**
 * A circle with a specified origin and radius.
 *
 * @param x The x coordinate of the center.
 * @param y The y coordinate of the center.
 * @param radius The radius of the circle.
 */
data class Circle(
    var x: Int,
    var y: Int,
    var radius: Int
) {
    /**
     * Calculates the diameter of the circle.
     */
    val diameter get() = radius * 2

    /**
     * Calculates the x coordinate of the upper-left corner of the circle.
     */
    val minX get() = x - radius

    /**
     * Calculates the y coordinate of the upper-left corner of the circle.
     */
    val minY get() = y - radius
}
