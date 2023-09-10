package ru.trforcex.uni.cg.primitives.app

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame

class DrawingApplication : JFrame("My Drawing Application") {
    init {
        setSize(640, 360)
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = false
        add(DrawingPanel(), BorderLayout.CENTER)
    }
}
