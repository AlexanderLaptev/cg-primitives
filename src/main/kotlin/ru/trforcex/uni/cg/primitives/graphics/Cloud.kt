package ru.trforcex.uni.cg.primitives.graphics

import ru.trforcex.uni.cg.primitives.util.Circle
import ru.trforcex.uni.cg.primitives.util.fillCircle
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

@Suppress("MemberVisibilityCanBePrivate")
class Cloud : Drawable {
    companion object {
        private val CIRCLE_COUNT_RANGE = 4..6
        private val RADIUS_RANGE = 20..40
    }

    var color: Color = Color.WHITE

    private val circles: LinkedList<Circle> = LinkedList()

    val firstRadius: Int get() = circles.first.radius
    val minX: Int get() = circles.first.x - circles.first.radius
    val maxX: Int get() = circles.last.x + circles.last.radius

    @Suppress("unused")
    val width: Int get() = maxX - minX

    init {
        generateCircles()
    }

    private fun generateCircles() {
        val count = Random.nextInt(CIRCLE_COUNT_RANGE) - 1
        var up = Random.nextBoolean()
        var lastCircle = Circle(0, 0, Random.nextInt(RADIUS_RANGE))
        circles += lastCircle

        repeat(count) {
            val radius = Random.nextInt(RADIUS_RANGE)
            val offsetX = Random.nextInt(lastCircle.radius, lastCircle.radius + radius / 2 + 1)
            val offsetY =
                (if (up) -1 else 1) * Random.nextInt(RADIUS_RANGE.first / 2, lastCircle.radius / 2 + 1)
            val newCircle = Circle(lastCircle.x + offsetX, lastCircle.y + offsetY, radius)
            lastCircle = newCircle
            up = !up
            circles += lastCircle
        }
    }

    override fun draw(g: Graphics2D, c: Component, originX: Int, originY: Int) {
        g.color = color
        for (circle in circles) {
            with(circle) {
                g.fillCircle(originX + x, originY + y, radius)
            }
        }
    }
}
