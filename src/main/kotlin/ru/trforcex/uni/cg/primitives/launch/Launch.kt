package ru.trforcex.uni.cg.primitives.launch

import ru.trforcex.uni.cg.primitives.app.DrawingApplication
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {  // Run all Swing stuff on the AWT thread.
        DrawingApplication().isVisible = true
    }
}
