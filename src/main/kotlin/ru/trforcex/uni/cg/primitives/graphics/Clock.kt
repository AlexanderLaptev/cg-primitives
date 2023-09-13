package ru.trforcex.uni.cg.primitives.graphics

import ru.trforcex.uni.cg.primitives.util.drawCircle
import ru.trforcex.uni.cg.primitives.util.fillCircle
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D
import java.time.LocalTime

class Clock : Drawable {
    companion object {
        private val HOUR_ANGLE = Math.toRadians(360.0 / 12.0)
        private const val HOURS = 12
        private const val HALF_PI = Math.PI / 2.0
        private const val MINUTES_IN_FULL_DAY = 12 * 60

        private const val RADIUS = 70
        private const val FRAME_THICKNESS = 8
        private const val TICK_THICKNESS = 3
        private const val TICK_EXTRA = 2
        private const val TICK_LENGTH = 12
        private const val HAND_THICKNESS = 6

        private val FRAME_STROKE = BasicStroke(FRAME_THICKNESS.toFloat())
        private val TICK_STROKE = BasicStroke(TICK_THICKNESS.toFloat())
        private val HAND_STROKE = BasicStroke(HAND_THICKNESS.toFloat(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)

        private val FRAME_COLOR = Color.LIGHT_GRAY
        private val TICK_COLOR = Color.decode("#858585")
        private val CLOCK_COLOR = Color.WHITE
        private val HAND_COLOR = Color.BLACK
    }

    override fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        drawClock(g, originX, originY)
        drawTicks(g, originX, originY)
        drawHands(g, originX, originY)
    }

    private fun drawClock(g: Graphics2D, originX: Int, originY: Int) {
        g.color = CLOCK_COLOR
        g.fillCircle(originX, originY, RADIUS)

        g.color = FRAME_COLOR
        g.stroke = FRAME_STROKE
        g.drawCircle(originX, originY, RADIUS)
    }

    @Suppress("MagicNumber")
    private fun drawTicks(g: Graphics2D, originX: Int, originY: Int) {
        val oldTransform = g.transform

        g.translate(originX, originY)
        g.color = TICK_COLOR
        g.stroke = TICK_STROKE
        repeat(HOURS) {
            g.drawLine(RADIUS - TICK_LENGTH, 0, RADIUS - FRAME_THICKNESS + TICK_EXTRA, 0)
            g.rotate(HOUR_ANGLE)
        }
        g.transform = oldTransform
    }

    @Suppress("MagicNumber")
    private fun drawHands(g: Graphics2D, originX: Int, originY: Int) {
        val systemTime = LocalTime.now()
        val minuteOfDay = systemTime.minute + systemTime.hour * 60
        val hourAngle = -HALF_PI + Math.toRadians(minuteOfDay.toDouble() / MINUTES_IN_FULL_DAY * 360.0)
        val minuteAngle = -HALF_PI + Math.toRadians(systemTime.minute / 60.0 * 360.0)

        val oldTransform = g.transform
        g.color = HAND_COLOR
        g.stroke = HAND_STROKE

        // Hour hand
        g.translate(originX, originY)
        g.rotate(hourAngle)
        g.drawLine(0, 0, RADIUS - FRAME_THICKNESS - 32, 0)

        // Minute hand
        g.transform = oldTransform
        g.translate(originX, originY)
        g.rotate(minuteAngle)
        g.drawLine(0, 0, RADIUS - FRAME_THICKNESS - 16, 0)
    }
}
