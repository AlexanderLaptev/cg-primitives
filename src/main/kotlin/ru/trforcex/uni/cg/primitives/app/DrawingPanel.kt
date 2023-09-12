package ru.trforcex.uni.cg.primitives.app

import ru.trforcex.uni.cg.primitives.graphics.Monitor
import ru.trforcex.uni.cg.primitives.util.clearComponentArea
import ru.trforcex.uni.cg.primitives.util.fillPolygonOffset
import ru.trforcex.uni.cg.primitives.util.setAntialiasingEnabled
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Polygon
import javax.swing.JPanel

/**
 * A panel which draws custom graphics.
 */
class DrawingPanel : JPanel() {
    private val monitor = Monitor()

    companion object {
        private val BACKGROUND_COLOR: Color = Color.decode("#dbc7a2")
        private val SKY_COLOR: Color = Color.decode("#a1c3ed")
        private val KEYBOARD_COLOR = Color.decode("#333333")
        private val TABLE_COLOR: Color = Color.decode("#a38967")

        private const val TABLE_HEIGHT = 140
        private const val TABLE_OFFSET = 200

        private const val KEYBOARD_HEIGHT = 50
        private const val KEYBOARD_WIDTH = 360
        private const val KEYBOARD_OFFSET = 40
        private const val KEYBOARD_Y = 300

        private const val KEYBOARD_CORD_THICKNESS = 4
        private const val KEYBOARD_CORD_WIDTH = 30
        private val KEYBOARD_CORD_STROKE = BasicStroke(KEYBOARD_CORD_THICKNESS.toFloat())
    }

    private lateinit var tablePolygon: Polygon
    private lateinit var keyboardPolygon: Polygon

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
        // This method is early enough for us to update all polygons and paths before any rendering occurs.
        updatePolygonsAndPaths()
    }

    private fun drawObjects(g: Graphics2D) {
        val centerX = width / 2
        val centerY = height / 2

        drawTable(g)
        monitor.draw(g, this, centerX, centerY)

        val keyboardX = centerX - KEYBOARD_WIDTH / 2
        val keyboardY = centerY + KEYBOARD_HEIGHT / 2 + KEYBOARD_Y
        drawKeyboard(g, keyboardX, keyboardY)
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
        keyboardPolygon = Polygon(
            intArrayOf(0, KEYBOARD_OFFSET, KEYBOARD_WIDTH - KEYBOARD_OFFSET, KEYBOARD_WIDTH),
            intArrayOf(0, -KEYBOARD_HEIGHT, -KEYBOARD_HEIGHT, 0),
            4
        )
        repaint()
    }

    private fun drawTable(g: Graphics2D) {
        if (!this::tablePolygon.isInitialized) updatePolygons()
        g.color = TABLE_COLOR
        g.fillPolygon(tablePolygon)
    }

    private fun drawKeyboard(g: Graphics2D, x: Int, y: Int) {
        if (!this::keyboardPolygon.isInitialized) updatePolygons()
        g.color = KEYBOARD_COLOR
        g.fillPolygonOffset(keyboardPolygon, x, y)
    }
}
