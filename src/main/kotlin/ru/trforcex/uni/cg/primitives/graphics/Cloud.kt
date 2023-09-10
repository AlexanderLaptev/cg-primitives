package ru.trforcex.uni.cg.primitives.graphics

import java.awt.Color
import java.awt.Graphics2D
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class Cloud : Drawable {
    companion object {
        private val CIRCLE_COUNT_RANGE = 3..7
        private val RADIUS_RANGE = 20..50
        private val OFFSET_RANGE = 40..50

        private val COLOR = Color.WHITE
    }

    private val circles: MutableList<Circle> = LinkedList()

    init {
        // Generate circles.
        val circleCount = Random.nextInt(CIRCLE_COUNT_RANGE)
        var x = 0
        var y = 0
        repeat(circleCount) {
            val circle = Circle(x, y, Random.nextInt(RADIUS_RANGE))
            x = Random.nextInt(OFFSET_RANGE)
            y = Random.nextInt(OFFSET_RANGE) * if (Random.nextBoolean()) 1 else -1
            circles += circle
        }
    }

    override fun draw(g: Graphics2D, originX: Int, originY: Int, angle: Float) {
        g.color = COLOR
//        g.transform.setToRotation(angle.toDouble())
        for (circle in circles) {
            with(circle) {
                g.fillOval(originX + x - radius, originY + y - radius, diameter, diameter)
            }
        }
    }
}
