package ru.trforcex.uni.cg.primitives.graphics

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D

class Window : Drawable {
    companion object {
        private const val WIDTH = 300
        private const val HEIGHT = 440
        private const val FRAME_THICKNESS = 20
        private const val HORIZONTAL_SECTION_HEIGHT = 150

        private const val CLOUD_BASE_Y = 60

        private val SKY_COLOR = Color.decode("#98cbe3")
        private val CLOUD_COLOR = Color.decode("#f5f8fa")
        private val FRAME_COLOR = Color.decode("#fff5ed")
        private val FRAME_STROKE = BasicStroke(FRAME_THICKNESS.toFloat())
    }

    private var cloud = Cloud().apply { color = CLOUD_COLOR }

    // Origin is the upper-left corner of the window.
    override fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        drawOutside(g, c, originX, originY)
        drawFrames(g, originX, originY)
    }

    private fun drawOutside(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        g.color = SKY_COLOR
        g.fillRect(originX, originY, WIDTH, HEIGHT)

        // Clip the window area
        g.clipRect(originX, originY, WIDTH, HEIGHT)
        cloud.draw(g, c, originX + 26, originY + CLOUD_BASE_Y)
        g.clip = null
    }

    private fun drawFrames(g: Graphics2D, originX: Int, originY: Int) {
        g.color = FRAME_COLOR
        g.stroke = FRAME_STROKE

        g.drawRect(originX, originY, WIDTH, HEIGHT) // Frame

        // Horizontal section
        val horizontalRight = originX + WIDTH
        val horizontalY = originY + HORIZONTAL_SECTION_HEIGHT
        g.drawLine(originX, horizontalY, horizontalRight, horizontalY)

        // Vertical sections
        val verticalX = originX + WIDTH / 2
        val verticalTop = originY + HORIZONTAL_SECTION_HEIGHT
        val verticalBottom = originY + HEIGHT
        g.drawLine(verticalX, verticalTop, verticalX, verticalBottom)
    }
}
