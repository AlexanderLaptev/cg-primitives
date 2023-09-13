package ru.trforcex.uni.cg.primitives.app

import ru.trforcex.uni.cg.primitives.graphics.Clock
import ru.trforcex.uni.cg.primitives.graphics.Cup
import ru.trforcex.uni.cg.primitives.graphics.Monitor
import ru.trforcex.uni.cg.primitives.graphics.Window
import ru.trforcex.uni.cg.primitives.util.clearComponentArea
import ru.trforcex.uni.cg.primitives.util.fillPolygonOffset
import ru.trforcex.uni.cg.primitives.util.setAntialiasingEnabled
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Polygon
import java.awt.event.ActionEvent
import java.awt.geom.Path2D
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * A panel which draws custom graphics.
 */
@Suppress("MagicNumber")
class DrawingPanel : JPanel() {
    private val monitor = Monitor()
    private val window = Window()
    private val cup = Cup()
    private val clock = Clock()

    companion object {
        private const val REDRAW_TIMER_DELAY = 33 // ~30 FPS
        private val PROGRESS_DELTA_RANGE = 1..3
        private val PROGRESS_TIME_RANGE = 1400..5500

        private val BACKGROUND_COLOR: Color = Color.decode("#dbc7a2")
        private val KEYBOARD_COLOR = Color.decode("#333333")
        private val KEYBOARD_CORD_COLOR = Color.decode("#2c2c2c")
        private val TABLE_COLOR: Color = Color.decode("#a38967")

        private const val TABLE_HEIGHT = 140
        private const val TABLE_OFFSET = 200

        private const val WINDOW_X = 130
        private const val WINDOW_Y = 50

        private const val KEYBOARD_HEIGHT = 50
        private const val KEYBOARD_WIDTH = 360
        private const val KEYBOARD_OFFSET = 40
        private const val KEYBOARD_Y = 300

        private const val KEYBOARD_CORD_WIDTH = 18
        private const val KEYBOARD_CORD_THICKNESS = 5
        private const val KEYBOARD_CORD_EDGE_OFFSET = 22
        private const val KEYBOARD_CORD_BEND_TIME = 0.74f
        private val KEYBOARD_CORD_STROKE = BasicStroke(
            KEYBOARD_CORD_THICKNESS.toFloat(),
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER
        )

        private const val MONITOR_CABLE_THICKNESS = 7
        private const val MONITOR_CABLE_X_OFFSET = 140
        private val MONITOR_CABLE_STROKE = BasicStroke(MONITOR_CABLE_THICKNESS.toFloat())

        private const val CUP_X = 260
        private const val CUP_Y_BOTTOM = 120

        private const val CLOCK_X = 940
        private const val CLOCK_Y = 90
    }

    private var centerX = 0
    private var centerY = 0

    private lateinit var tablePolygon: Polygon
    private var keyboardPolygon = Polygon(
        intArrayOf(0, KEYBOARD_OFFSET, KEYBOARD_WIDTH - KEYBOARD_OFFSET, KEYBOARD_WIDTH),
        intArrayOf(0, -KEYBOARD_HEIGHT, -KEYBOARD_HEIGHT, 0),
        4
    )
    private lateinit var keyboardCordCurve: Path2D.Float

    private val redrawTimer = Timer(REDRAW_TIMER_DELAY) {
        window.animateClouds(REDRAW_TIMER_DELAY)
        repaint()
    }

    private val progressTimer: Timer = Timer(Random.nextInt(PROGRESS_TIME_RANGE), this::progressTimerListener)

    init {
        redrawTimer.isRepeats = true
        progressTimer.isRepeats = false
        redrawTimer.start()
        progressTimer.start()
    }

    @Suppress("UnusedParameter")
    private fun progressTimerListener(e: ActionEvent?) {
        monitor.progress += Random.nextInt(PROGRESS_DELTA_RANGE)
        progressTimer.delay = Random.nextInt(PROGRESS_DELTA_RANGE)
        progressTimer.start()
    }

    override fun paintComponent(g: Graphics?) {
        g as Graphics2D

        super.paintComponents(g)
        g.setAntialiasingEnabled(true)
        g.background = BACKGROUND_COLOR
        g.clearComponentArea(this)
        drawObjects(g)
    }

    override fun doLayout() {
        super.doLayout()
        centerX = width / 2
        centerY = height / 2
        // This method is early enough for us to update all polygons and paths before any rendering occurs.
        updatePolygonsAndPaths()
    }

    private fun drawObjects(g: Graphics2D) {
        window.draw(g, this, WINDOW_X, WINDOW_Y)
        drawMonitorCable(g)
        drawTable(g)
        monitor.draw(g, this, centerX, centerY)
        drawKeyboard(g)
        cup.draw(g, this, CUP_X, height - CUP_Y_BOTTOM)
        clock.draw(g, this, CLOCK_X, CLOCK_Y)
    }

    private fun drawMonitorCable(g: Graphics2D) {
        g.color = Monitor.STAND_COLOR
        g.stroke = MONITOR_CABLE_STROKE
        val x = centerX - Monitor.WIDTH / 2 + MONITOR_CABLE_X_OFFSET
        g.drawLine(x, centerY + Monitor.HEIGHT / 2, x + 2, height - TABLE_HEIGHT)
    }

    override fun setSize(width: Int, height: Int) {
        super.setSize(width, height)
        updatePolygonsAndPaths()
    }

    private fun updatePolygonsAndPaths() {
        tablePolygon = Polygon(
            intArrayOf(0, TABLE_OFFSET, width - TABLE_OFFSET, width),
            intArrayOf(height, height - TABLE_HEIGHT, height - TABLE_HEIGHT, height),
            4
        )
        keyboardCordCurve = Path2D.Float().apply {
            val cordTop: Float = (height - TABLE_HEIGHT).toFloat()
            val cordBottom: Float = (centerY + KEYBOARD_Y - KEYBOARD_HEIGHT / 2).toFloat()
            val cordHeight = cordBottom - cordTop

            val cordLeft: Float = (centerX + KEYBOARD_WIDTH / 2 - KEYBOARD_OFFSET - KEYBOARD_CORD_EDGE_OFFSET).toFloat()
            val cordRight: Float = cordLeft + KEYBOARD_CORD_WIDTH

            val yLeft = cordBottom - cordHeight * KEYBOARD_CORD_BEND_TIME
            val yRight = cordTop + cordHeight * (1.0f - KEYBOARD_CORD_BEND_TIME)
            moveTo(cordLeft, cordBottom)
            curveTo(
                cordLeft, yLeft,
                cordRight, yRight,
                cordRight, cordTop
            )
        }
        repaint()
    }

    private fun drawTable(g: Graphics2D) {
        g.color = TABLE_COLOR
        g.fillPolygon(tablePolygon)
    }

    private fun drawKeyboard(g: Graphics2D) {
        val keyboardX = centerX - KEYBOARD_WIDTH / 2
        val keyboardY = centerY + KEYBOARD_HEIGHT / 2 + KEYBOARD_Y
        g.color = KEYBOARD_COLOR
        g.fillPolygonOffset(keyboardPolygon, keyboardX, keyboardY)
        drawKeyboardCord(g)
    }

    private fun drawKeyboardCord(g: Graphics2D) {
        g.color = KEYBOARD_CORD_COLOR
        g.stroke = KEYBOARD_CORD_STROKE
        g.draw(keyboardCordCurve)
    }
}
