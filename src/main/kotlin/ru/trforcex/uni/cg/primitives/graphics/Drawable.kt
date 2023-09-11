package ru.trforcex.uni.cg.primitives.graphics

import java.awt.Component
import java.awt.Graphics2D

/**
 * An object which can be drawn using [Graphics2D].
 */
interface Drawable {
    /**
     * Draws this object.
     *
     * @param g The graphics.
     * @param c The parent component.
     * @param originX The x coordinate of the object origin.
     * @param originY The y coordinate of the object origin.
     */
    fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int)
}
