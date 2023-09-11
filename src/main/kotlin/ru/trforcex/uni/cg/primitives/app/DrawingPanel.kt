package ru.trforcex.uni.cg.primitives.app

import ru.trforcex.uni.cg.primitives.graphics.Cloud
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
    private val cloud = Cloud()

    companion object {
        val BACKGROUND_COLOR: Color = Color.decode("#dbc7a2")


        const val TABLE_HEIGHT = 140
        const val TABLE_OFFSET = 200
    }

    private lateinit var tablePolygon: Polygon

    override fun paintComponent(g: Graphics?) {
        g as Graphics2D

        super.paintComponents(g)
        g.setAntialiasingEnabled(true)
        g.background = BACKGROUND_COLOR
        g.clearComponentArea(this)
        cloud.draw(g, this,100, 100)
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
