package ru.trforcex.uni.cg.primitives.util

import ru.trforcex.uni.cg.primitives.graphics.Circle
import java.awt.Component
import java.awt.Graphics2D
import java.awt.RenderingHints

/**
 * Clears the area of the [component] with the current background color.
 */
fun Graphics2D.clearComponentArea(component: Component) {
    clearRect(0, 0, component.width, component.height)
}

/**
 * Enables or disables antialiasing.
 */
fun Graphics2D.setAntialiasingEnabled(enabled: Boolean) {
    setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        if (enabled) RenderingHints.VALUE_ANTIALIAS_ON else RenderingHints.VALUE_ANTIALIAS_OFF
    )
}

/**
 * Fills the specified circle.
 *
 * @param circle The circle to fill.
 */
fun Graphics2D.fillCircle(circle: Circle) {
    fillOval(circle.minX, circle.minY, circle.diameter, circle.diameter)
}

/**
 * Fills the specified circle.
 *
 * @param centerX The x coordinate of the center.
 * @param centerY The y coordinate of the center.
 */
fun Graphics2D.fillCircle(centerX: Int, centerY: Int, radius: Int) {
    val diameter = 2 * radius
    fillOval(centerX - radius, centerY - radius, diameter, diameter)
}
