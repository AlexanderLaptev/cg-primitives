package ru.trforcex.uni.cg.primitives.graphics

import java.awt.Graphics2D

/**
 * An object which can be drawn using [Graphics2D].
 */
interface Drawable {
    fun draw(g: Graphics2D, originX: Int, originY: Int, angle: Float)
}
