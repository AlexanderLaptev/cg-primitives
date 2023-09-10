package ru.trforcex.uni.cg.primitives.app

import java.awt.BorderLayout
import javax.swing.JFrame

/**
 * The main class of the drawing application.
 */
class DrawingApplication : JFrame("My Drawing Application") {
    companion object {
        /**
         * The width of the window in pixels.
         */
        const val WINDOW_WIDTH = 640

        /**
         * The height of the window in pixels.
         */
        const val WINDOW_HEIGHT = 360
    }

    init {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = false
        add(DrawingPanel(), BorderLayout.CENTER)
    }
}
