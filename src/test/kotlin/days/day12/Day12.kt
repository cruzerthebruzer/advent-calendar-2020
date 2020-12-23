package days.day12

import org.junit.Assert
import org.junit.Test

class Day12Test {
    @Test
    fun `day12 should handle simple command correctly`() {
        val day12 = Day12()
        val result = day12.invoke(listOf("F10"))
        Assert.assertEquals(10, result)
    }

    @Test
    fun `day12 should handle simple commands correctly`() {
        val day12 = Day12()
        val result = day12.invoke(listOf("F10", "N3"))
        Assert.assertEquals(13, result)
    }

    @Test
    fun `day12 should handle simple mixed commands correctly`() {
        val day12 = Day12()
        val result = day12.invoke(listOf("F10", "N3", "R90"))
        Assert.assertEquals(13, result)
    }

    @Test
    fun `day12 should handle turned forward correctly`() {
        val day12 = Day12()
        val result = day12.invoke(listOf("F10", "N3", "F7", "R90", "F11"))
        Assert.assertEquals(25, result)
    }
}