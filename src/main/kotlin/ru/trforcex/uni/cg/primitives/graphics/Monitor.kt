package ru.trforcex.uni.cg.primitives.graphics

import ru.trforcex.uni.cg.primitives.util.fillOvalCentered
import ru.trforcex.uni.cg.primitives.util.fillRectCentered
import java.awt.Color
import java.awt.Component
import java.awt.Font
import java.awt.Graphics2D

class Monitor : Drawable {
    companion object {
        val CASE_COLOR: Color = Color.decode("#333333")
        val STAND_COLOR: Color = Color.decode("#2a2a2a")
        val BASE_COLOR: Color = Color.decode("#373737")
        val BSOD_TEXT_COLOR: Color = Color.WHITE
        val SCREEN_COLOR: Color = Color.decode("#0078d7") // Windows 10 BSOD color.

        const val WIDTH = 550
        const val HEIGHT = WIDTH / 16 * 9

        private const val BORDER_THICKNESS_TOP = 6
        private const val BORDER_THICKNESS_SIDES = 6
        private const val BORDER_THICKNESS_BOTTOM = 14

        private const val SCREEN_WIDTH = WIDTH - BORDER_THICKNESS_SIDES * 2
        private const val SCREEN_HEIGHT = HEIGHT - BORDER_THICKNESS_TOP - BORDER_THICKNESS_BOTTOM

        private const val STAND_HEIGHT = 60
        private const val STAND_WIDTH = 70

        private const val BASE_WIDTH = 210
        private const val BASE_HEIGHT = 60
        private const val BASE_Y_OFFSET = 20

        private const val BSOD_TEXT_FACE = ":("

        @Suppress("GrazieInspection")
        private val BSOD_TEXT_MESSAGE_LINES = """
            Your  PC  ran into a problem and needs
            to  restart.  We're  just  collecting some
            error   info,  and  then  we'll  restart  for
            you.
        """.trimIndent().lines()

        private val BSOD_FACE_FONT = Font("Segoe UI", Font.PLAIN, 80)
        private val BSOD_MESSAGE_FONT = Font("Segoe UI", Font.PLAIN, 20)
        private val BSOD_PROGRESS_FONT = Font("Segoe UI", Font.PLAIN, 26)

        private const val BSOD_LEFT = 30
        private const val BSOD_FACE_Y = -40
        private const val BSOD_MESSAGE_TOP_PADDING = 8
        private const val BSOD_MESSAGE_LINE_SPACING = 3
        private const val BSOD_PROGRESS_TOP_PADDING = 6
    }

    private val progressText: String
        get() {
            return "$progress% complete"
        }
    var progress: Int = 0

    override fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        // Calculations
        val caseBottom = originY + HEIGHT / 2

        drawCase(g, originX, originY)
        drawScreen(g, originX, originY)
        drawText(g, originX, originY)
        drawBase(g, originX, caseBottom)
        drawStand(g, originX, caseBottom)
    }

    private fun drawText(g: Graphics2D, originX: Int, originY: Int) {
        g.color = BSOD_TEXT_COLOR
        val x = originX - SCREEN_WIDTH / 2 + BSOD_LEFT

        // Sad face
        g.font = BSOD_FACE_FONT
        g.drawString(BSOD_TEXT_FACE, x, originY + BSOD_FACE_Y)

        // Message
        var lastY = 0
        g.font = BSOD_MESSAGE_FONT
        for ((i, line) in BSOD_TEXT_MESSAGE_LINES.withIndex()) {
            lastY = originY + BSOD_MESSAGE_TOP_PADDING + (BSOD_MESSAGE_FONT.size + BSOD_MESSAGE_LINE_SPACING) * i
            g.drawString(line, x, lastY)
        }

        // Progress
        g.font = BSOD_PROGRESS_FONT
        g.drawString(progressText, x, lastY + BSOD_PROGRESS_FONT.size + BSOD_PROGRESS_TOP_PADDING)
    }

    private fun drawCase(g: Graphics2D, originX: Int, originY: Int) {
        g.color = CASE_COLOR
        g.fillRectCentered(originX, originY, WIDTH, HEIGHT)
    }

    private fun drawScreen(g: Graphics2D, originX: Int, originY: Int) {
        g.color = SCREEN_COLOR
        g.fillRectCentered(
            originX,
            originY - (BORDER_THICKNESS_BOTTOM - BORDER_THICKNESS_TOP) / 2,
            SCREEN_WIDTH,
            SCREEN_HEIGHT
        )
    }

    private fun drawBase(g: Graphics2D, originX: Int, caseBottom: Int) {
        g.color = BASE_COLOR
        g.fillOvalCentered(originX, caseBottom + STAND_HEIGHT + BASE_Y_OFFSET, BASE_WIDTH, BASE_HEIGHT)
    }

    private fun drawStand(g: Graphics2D, originX: Int, caseBottom: Int) {
        g.color = STAND_COLOR
        g.fillRectCentered(originX, caseBottom + STAND_WIDTH / 2, STAND_HEIGHT, STAND_WIDTH)
    }
}
