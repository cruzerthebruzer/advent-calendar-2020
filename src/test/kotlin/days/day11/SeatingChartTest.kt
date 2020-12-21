package days.day11

import org.junit.Assert
import org.junit.Test

class SeatingChartTest {
    @Test
    fun `getAdjacentSeatingExpandedBreakdown creates correct breakdown on simple case`() {
        val breakdown = simpleSeatingBreakdown.getAdjacentSeatingExpandedBreakdown(1, 1)
        Assert.assertEquals(breakdown.topLeftArea, PlaneArea.FLOOR)
        Assert.assertEquals(breakdown.topMiddleArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.topRightArea, PlaneArea.EMPTY)
        Assert.assertEquals(breakdown.middleLeftArea, PlaneArea.EMPTY)
        Assert.assertEquals(breakdown.middleRightArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.bottomLeftArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.bottomMiddleArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.bottomRightArea, PlaneArea.EMPTY)
    }

    @Test
    fun `getAdjacentSeatingExpandedBreakdown creates correct breakdown on difficult case`() {
        val breakdown = difficultSeatingBreakdown.getAdjacentSeatingExpandedBreakdown(3, 2)

        Assert.assertEquals(breakdown.topLeftArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.topMiddleArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.topRightArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.middleLeftArea, PlaneArea.WALL)
        Assert.assertEquals(breakdown.middleRightArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.bottomLeftArea, PlaneArea.OCCUPIED)
        Assert.assertEquals(breakdown.bottomMiddleArea, PlaneArea.EMPTY)
        Assert.assertEquals(breakdown.bottomRightArea, PlaneArea.EMPTY)
    }
}

val simpleSeatingBreakdown = SeatingChart(
    listOf(
        listOf(PlaneArea.FLOOR, PlaneArea.OCCUPIED, PlaneArea.EMPTY),
        listOf(PlaneArea.EMPTY, PlaneArea.OCCUPIED, PlaneArea.OCCUPIED),
        listOf(PlaneArea.OCCUPIED, PlaneArea.OCCUPIED, PlaneArea.EMPTY),
    )
)

val difficultSeatingBreakdown = SeatingChart(
    listOf(
        listOf(PlaneArea.FLOOR, PlaneArea.OCCUPIED, PlaneArea.EMPTY, PlaneArea.EMPTY, PlaneArea.FLOOR),
        listOf(PlaneArea.EMPTY, PlaneArea.OCCUPIED, PlaneArea.OCCUPIED, PlaneArea.EMPTY, PlaneArea.OCCUPIED),
        listOf(PlaneArea.OCCUPIED, PlaneArea.OCCUPIED, PlaneArea.FLOOR, PlaneArea.FLOOR, PlaneArea.OCCUPIED),
        listOf(PlaneArea.FLOOR, PlaneArea.FLOOR, PlaneArea.EMPTY, PlaneArea.FLOOR, PlaneArea.OCCUPIED),
        listOf(PlaneArea.OCCUPIED, PlaneArea.FLOOR, PlaneArea.EMPTY, PlaneArea.EMPTY, PlaneArea.OCCUPIED),
        listOf(PlaneArea.OCCUPIED, PlaneArea.FLOOR, PlaneArea.EMPTY, PlaneArea.EMPTY, PlaneArea.OCCUPIED),
    )
)