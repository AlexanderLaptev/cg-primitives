package ru.trforcex.uni.cg.primitives.graphics

import ru.trforcex.uni.cg.primitives.util.drawCircle
import ru.trforcex.uni.cg.primitives.util.fillCircle
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D

class Clock : Drawable {
    companion object {
        private val HOUR_ANGLE = Math.toRadians(360.0 / 12.0)

        private const val RADIUS = 70
        private const val FRAME_THICKNESS = 8
        private const val TICK_THICKNESS = 4

        private val FRAME_STROKE = BasicStroke(FRAME_THICKNESS.toFloat())
        private val TICK_STROKE = BasicStroke(TICK_THICKNESS.toFloat())

        private val FRAME_COLOR = Color.LIGHT_GRAY
        private val TICK_COLOR = Color.decode("#858585")
        private val CLOCK_COLOR = Color.WHITE
    }

    override fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        drawClock(g, c, originX, originY)
        drawTicks(g, c, originX, originY)
    }

    private fun drawClock(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        g.color = CLOCK_COLOR
        g.fillCircle(originX, originY, RADIUS)

        g.color = FRAME_COLOR
        g.stroke = FRAME_STROKE
        g.drawCircle(originX, originY, RADIUS)
    }

    @Suppress("MagicNumber")
    private fun drawTicks(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        val oldTransform = g.transform

        g.translate(originX, originY)
        g.color = TICK_COLOR
        g.stroke = TICK_STROKE
        repeat(12) {
            g.drawLine(RADIUS - 18, 0, RADIUS - FRAME_THICKNESS + 2, 0)
            g.rotate(HOUR_ANGLE)
        }
        g.transform = oldTransform
    }
}
