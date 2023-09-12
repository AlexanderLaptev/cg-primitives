package ru.trforcex.uni.cg.primitives.graphics

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Component
import java.awt.Font
import java.awt.Graphics2D

class Cup : Drawable {
    companion object {
        private const val WIDTH = 70
        private const val HEIGHT = 64
        private const val OVAL_HEIGHT = 30

        private const val HANDLE_THICKNESS = 6
        private const val HANDLE_Y = 16
        private const val HANDLE_SIZE = 36
        private val HANDLE_STROKE = BasicStroke(HANDLE_THICKNESS.toFloat())

        private const val ICON_TEXT = "\u2665" // ♥
        private val ICON_FONT = Font("Segoe UI", Font.PLAIN, 54)

        private val OUTSIDE_COLOR = Color.WHITE
        private val INSIDE_COLOR = Color.decode("#cfcfcf")
        private val ICON_COLOR = Color.decode("#ff2929")
    }

    override fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        val halfOvalHeight = OVAL_HEIGHT / 2

        // Bottom and middle
        g.color = OUTSIDE_COLOR
        g.fillRect(originX, originY, WIDTH, HEIGHT)
        g.fillOval(originX, originY + HEIGHT - halfOvalHeight, WIDTH, OVAL_HEIGHT)

        // Handle
        g.stroke = HANDLE_STROKE
        g.drawArc(originX - HANDLE_SIZE / 2, originY + HANDLE_Y, HANDLE_SIZE, HANDLE_SIZE, 90, 180)

        // Icon
        g.color = ICON_COLOR
        g.font = ICON_FONT
        val stringWidth = g.fontMetrics.stringWidth(ICON_TEXT)
        g.drawString(
            ICON_TEXT,
            originX + WIDTH / 2 - stringWidth / 2,
            originY + (HEIGHT + ICON_FONT.size) / 2,
        )

        // Top oval
        g.color = INSIDE_COLOR
        g.fillOval(originX, originY - halfOvalHeight, WIDTH, OVAL_HEIGHT)
    }
}
