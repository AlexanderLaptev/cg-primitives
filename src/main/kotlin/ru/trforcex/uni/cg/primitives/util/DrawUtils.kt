@file:Suppress("unused")

package ru.trforcex.uni.cg.primitives.util

import java.awt.Component
import java.awt.Graphics2D
import java.awt.Polygon
import java.awt.RenderingHints

/**
 * Clears the area of the [component] with the current background color.
 */
fun Graphics2D.clearComponentArea(component: Component) {
    clearRect(0, 0, component.width, component.height)
}

/**
 * Enables or disables antialiasing for both the geometry and text.
 */
fun Graphics2D.setAntialiasingEnabled(enabled: Boolean) {
    setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        if (enabled) RenderingHints.VALUE_ANTIALIAS_ON else RenderingHints.VALUE_ANTIALIAS_OFF
    )
    setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING,
        if (enabled) RenderingHints.VALUE_TEXT_ANTIALIAS_ON else RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
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

/**
 * Draws the specified circle.
 *
 * @param circle The circle to fill.
 */
fun Graphics2D.drawCircle(circle: Circle) {
    drawOval(circle.minX, circle.minY, circle.diameter, circle.diameter)
}

/**
 * Draws the specified circle.
 *
 * @param centerX The x coordinate of the center.
 * @param centerY The y coordinate of the center.
 */
fun Graphics2D.drawCircle(centerX: Int, centerY: Int, radius: Int) {
    val diameter = 2 * radius
    drawOval(centerX - radius, centerY - radius, diameter, diameter)
}

/**
 * Fills the specified rectangle.
 *
 * @param centerX The x coordinate of the center.
 * @param centerY The y coordinate of the center.
 * @param width The width of the rectangle.
 * @param height The height of the rectangle.
 */
fun Graphics2D.fillRectCentered(centerX: Int, centerY: Int, width: Int, height: Int) {
    val halfWidth = width / 2
    val halfHeight = height / 2
    fillRect(centerX - halfWidth, centerY - halfHeight, width, height)
}

/**
 * Fills the specified oval.
 *
 * @param centerX The x coordinate of the center.
 * @param centerY The y coordinate of the center.
 * @param width The width of the oval.
 * @param height The height of the oval.
 */
fun Graphics2D.fillOvalCentered(centerX: Int, centerY: Int, width: Int, height: Int) {
    val halfWidth = width / 2
    val halfHeight = height / 2
    fillOval(centerX - halfWidth, centerY - halfHeight, width, height)
}

/**
 * Fills the specified polygon, offsetting all of its vertices by the specified amount.
 *
 * @param polygon The polygon to fill.
 * @param offsetX The x offset.
 * @param offsetY The y offset.
 */
fun Graphics2D.fillPolygonOffset(polygon: Polygon, offsetX: Int, offsetY: Int) {
    val xPoints = IntArray(polygon.xpoints.size)
    val yPoints = IntArray(polygon.ypoints.size)
    for (i in xPoints.indices) {
        xPoints[i] += polygon.xpoints[i] + offsetX
        yPoints[i] += polygon.ypoints[i] + offsetY
    }
    fillPolygon(xPoints, yPoints, polygon.npoints)
}
