package ru.trforcex.uni.cg.primitives.util

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
