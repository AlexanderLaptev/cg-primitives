package ru.trforcex.uni.cg.primitives.graphics

import ru.trforcex.uni.cg.primitives.util.fillOvalCentered
import ru.trforcex.uni.cg.primitives.util.fillRectCentered
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D

class Monitor : Drawable {
    companion object {
        private val CASE_COLOR = Color.decode("#333333")
        private val SCREEN_COLOR = Color.decode("#0078d7") // Windows 10 BSOD color.

        private const val WIDTH = 550
        private const val HEIGHT = WIDTH / 16 * 9

        private const val BORDER_THICKNESS_TOP = 6
        private const val BORDER_THICKNESS_SIDES = 6
        private const val BORDER_THICKNESS_BOTTOM = 14

        private const val SCREEN_WIDTH = WIDTH - BORDER_THICKNESS_SIDES * 2
        private const val SCREEN_HEIGHT = HEIGHT - BORDER_THICKNESS_TOP - BORDER_THICKNESS_BOTTOM

        private val STAND_COLOR = Color.decode("#2A2A2A")
        private const val STAND_HEIGHT = 60
        private const val STAND_WIDTH = 70

        private val BASE_COLOR = Color.decode("#373737")
        private const val BASE_WIDTH = 210
        private const val BASE_HEIGHT = 60
        private const val BASE_Y_OFFSET = 20
    }

    override fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        // Calculations
        val caseBottom = originY + HEIGHT / 2

        // Draw case
        g.color = CASE_COLOR
        g.fillRectCentered(originX, originY, WIDTH, HEIGHT)

        // Draw screen
        g.color = SCREEN_COLOR
        g.fillRectCentered(
            originX,
            originY - (BORDER_THICKNESS_BOTTOM - BORDER_THICKNESS_TOP) / 2,
            SCREEN_WIDTH,
            SCREEN_HEIGHT
        )

        // Draw base
        g.color = BASE_COLOR
        g.fillOvalCentered(originX, caseBottom + STAND_HEIGHT + BASE_Y_OFFSET, BASE_WIDTH, BASE_HEIGHT)

        // Draw stand
        g.color = STAND_COLOR
        g.fillRectCentered(originX, caseBottom + STAND_WIDTH / 2, STAND_HEIGHT, STAND_WIDTH)
    }
}
