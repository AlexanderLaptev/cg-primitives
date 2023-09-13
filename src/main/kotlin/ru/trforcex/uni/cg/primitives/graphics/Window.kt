package ru.trforcex.uni.cg.primitives.graphics

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt

class Window : Drawable {
    companion object {
        private const val WIDTH = 300
        private const val HEIGHT = 440
        private const val FRAME_THICKNESS = 20
        private const val HORIZONTAL_SECTION_HEIGHT = 150

        private val CLOUD_Y_RANGE = 40..150
        private val CLOUD_SPEED_RANGE = 10..80

        private val SKY_COLOR = Color.decode("#98cbe3")
        private val CLOUD_COLOR = Color.decode("#f5f8fa")
        private val FRAME_COLOR = Color.decode("#dbd9d7")
        private val FRAME_STROKE = BasicStroke(FRAME_THICKNESS.toFloat())
    }

    private lateinit var cloud: Cloud
    private var cloudX = 0.0f
    private var cloudY = 0
    private var cloudSpeed = 0

    init {
        generateNewCloud()
    }

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
        cloud.draw(g, c, originX + cloudX.roundToInt(), originY + cloudY)
        g.clip = null
    }

    /**
     * Moves the cloud and regenerates it if it goes offscreen.
     *
     * @param deltaTime Delta time in milliseconds
     */
    @Suppress("MagicNumber")
    fun animateClouds(deltaTime: Int) {
        val seconds = deltaTime / 1000.0f
        cloudX += (cloudSpeed * seconds).roundToInt()
        if (cloudX > WIDTH + cloud.firstRadius) generateNewCloud()
    }

    private fun generateNewCloud() {
        cloud = Cloud().apply { color = CLOUD_COLOR }
        cloudX = -cloud.maxX.toFloat()
        cloudY = Random.nextInt(CLOUD_Y_RANGE)
        cloudSpeed = Random.nextInt(CLOUD_SPEED_RANGE)
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
