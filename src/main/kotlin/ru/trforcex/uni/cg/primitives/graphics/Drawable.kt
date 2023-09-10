package ru.trforcex.uni.cg.primitives.graphics

import java.awt.Graphics2D

/**
 * An object which can be drawn using [Graphics2D].
 */
interface Drawable {
    /**
     * Draws this object.
     *
     * @param g The graphics.
     * @param originX The x coordinate of the object origin.
     * @param originY The y coordinate of the object origin.
     */
    fun draw(g: Graphics2D, originX: Int, originY: Int)
}
