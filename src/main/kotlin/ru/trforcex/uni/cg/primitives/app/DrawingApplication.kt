package ru.trforcex.uni.cg.primitives.app

import java.awt.BorderLayout
import javax.swing.JFrame

/**
 * The main class of the drawing application.
 */
class DrawingApplication : JFrame("My Drawing Application") {
    private val drawingPanel = DrawingPanel()

    companion object {
        /**
         * The width of the window in pixels.
         */
        const val WINDOW_WIDTH = 1280

        /**
         * The height of the window in pixels.
         */
        const val WINDOW_HEIGHT = 720
    }

    init {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = false
        add(drawingPanel, BorderLayout.CENTER)
        setLocationRelativeTo(null) // Center the window.
    }
}
