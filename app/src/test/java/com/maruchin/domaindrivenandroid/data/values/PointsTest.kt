package com.maruchin.domaindrivenandroid.data.values

import org.junit.Assert.*
import org.junit.Test

class PointsTest {

    @Test
    fun `Points can be zero`() {
        Points(0)
    }

    @Test
    fun `Points can be positive`() {
        Points(100)
    }

    @Test
    fun `Points can't be negative`() {
        assertThrows(IllegalArgumentException::class.java) {
            Points(-100)
        }
    }

    @Test
    fun `Compare points`() {
        val morePoints = Points(100)
        val lessPoints = Points(50)

        assertTrue(morePoints > lessPoints)
    }

    @Test
    fun `Plus points`() {
        val points = Points(100)
        val otherPoints = Points(50)

        val sum = points + otherPoints

        assertEquals(Points(150), sum)
    }

    @Test
    fun `Minus points`() {
        val points = Points(100)
        val otherPoints = Points(50)

        val diff = points - otherPoints

        assertEquals(Points(50), diff)
    }
}
