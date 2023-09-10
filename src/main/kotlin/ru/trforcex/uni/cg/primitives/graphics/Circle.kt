package ru.trforcex.uni.cg.primitives.graphics

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
    val diameter get() = radius * 2
}
