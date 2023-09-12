package ru.trforcex.uni.cg.primitives.graphics

import ru.trforcex.uni.cg.primitives.util.drawCircle
import ru.trforcex.uni.cg.primitives.util.fillCircle
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D

class Clock : Drawable {
    companion object {
        private const val RADIUS = 70
        private const val FRAME_THICKNESS = 8

        private val FRAME_STROKE = BasicStroke(FRAME_THICKNESS.toFloat())

        private val FRAME_COLOR = Color.LIGHT_GRAY
        private val CLOCK_COLOR = Color.WHITE
    }

    override fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        g.color = CLOCK_COLOR
        g.fillCircle(originX, originY, RADIUS)

        g.color = FRAME_COLOR
        g.stroke = FRAME_STROKE
        g.drawCircle(originX, originY, RADIUS)
    }
}
