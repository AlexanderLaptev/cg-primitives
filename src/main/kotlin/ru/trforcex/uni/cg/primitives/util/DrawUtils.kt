package ru.trforcex.uni.cg.primitives.util

import java.awt.Component
import java.awt.Graphics2D

/**
 * Clears the area of the [Component].
 */
fun Component.clear(g: Graphics2D) {
    g.clearRect(0, 0, width, height)
}
