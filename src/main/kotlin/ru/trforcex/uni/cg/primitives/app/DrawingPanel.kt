package ru.trforcex.uni.cg.primitives.app

import ru.trforcex.uni.cg.primitives.graphics.Monitor
import ru.trforcex.uni.cg.primitives.util.clearComponentArea
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
        val BACKGROUND_COLOR: Color = Color.decode("#dbc7a2")
        val SKY_COLOR: Color = Color.decode("#a1c3ed")

        const val TABLE_HEIGHT = 140
        const val TABLE_OFFSET = 200

        const val HALF_MONITOR_WIDTH = 350 / 2
        const val HALF_MONITOR_HEIGHT = HALF_MONITOR_WIDTH / 16 * 9
    }

    private lateinit var tablePolygon: Polygon

    override fun paintComponent(g: Graphics?) {
        g as Graphics2D

        super.paintComponents(g)
        g.setAntialiasingEnabled(true)
        g.background = BACKGROUND_COLOR
        g.clearComponentArea(this)
        drawStuff(g)
    }

    private fun drawStuff(g: Graphics2D) {
        // Do maths
        val centerX = width / 2
        val centerY = height / 2

        // Draw table
        if (!this::tablePolygon.isInitialized) updateTablePolygon()
        g.color = Color.decode("#a38967")
        g.fillPolygon(tablePolygon)

        // Draw monitor
        monitor.draw(g, this, centerX, centerY)
    }

    override fun setSize(width: Int, height: Int) {
        super.setSize(width, height)
        updateTablePolygon()
    }

    private fun updateTablePolygon() {
        tablePolygon = Polygon(
            intArrayOf(0, TABLE_OFFSET, width - TABLE_OFFSET, width),
            intArrayOf(height, height - TABLE_HEIGHT, height - TABLE_HEIGHT, height),
            4
        )
    }
}
