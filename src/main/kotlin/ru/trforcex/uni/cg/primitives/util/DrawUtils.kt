package ru.trforcex.uni.cg.primitives.util

import java.awt.Component
import java.awt.Graphics2D
import java.awt.RenderingHints

/**
 * Clears the area of the [Component].
 */
fun Component.clear(g: Graphics2D) {
    g.clearRect(0, 0, width, height)
}

/**
 * Enables or disables antialiasing.
 */
fun Component.setAntialiasingEnabled(g: Graphics2D, enabled: Boolean) {
    g.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        if (enabled) RenderingHints.VALUE_ANTIALIAS_ON else RenderingHints.VALUE_ANTIALIAS_OFF
    )
}
