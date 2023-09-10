package ru.trforcex.uni.cg.primitives.app

import ru.trforcex.uni.cg.primitives.graphics.Cloud
import ru.trforcex.uni.cg.primitives.util.clearComponentArea
import ru.trforcex.uni.cg.primitives.util.setAntialiasingEnabled
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

/**
 * A panel which draws custom graphics.
 */
class DrawingPanel : JPanel() {
    private val cloud = Cloud()

    companion object {
        /**
         * The background color.
         */
        val BACKGROUND_COLOR: Color = Color.decode("#dbc7a2")
    }

    override fun paintComponent(g: Graphics?) {
        g as Graphics2D

        super.paintComponents(g)
        g.setAntialiasingEnabled(true)
        g.background = BACKGROUND_COLOR
        g.clearComponentArea(this)
        cloud.draw(g, 100, 100, 45.0f)
    }
}
