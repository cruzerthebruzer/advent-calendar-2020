package days.day11

import kotlin.reflect.full.memberProperties

enum class PlaneArea {
    FLOOR, OCCUPIED, EMPTY, WALL;

    companion object {
        fun fromChar(char: Char) = when (char) {
            'L' -> EMPTY
            '#' -> OCCUPIED
            '.' -> FLOOR
            else -> throw Error("Unexpected PlaneArea $char")
        }
    }
}

fun PlaneArea?.isFloorOrNull() = this == PlaneArea.FLOOR || this == null

data class SeatingBreakdown(
    val emptyCount: Int = 0,
    val floorCount: Int = 0,
    val occupiedCount: Int = 0,
    val wallCount: Int = 0
)

data class SeatingAdjacencyBreakdown(
    var topLeftArea: PlaneArea? = null,
    var topMiddleArea: PlaneArea? = null,
    var topRightArea: PlaneArea? = null,
    var middleLeftArea: PlaneArea? = null,
    var middleRightArea: PlaneArea? = null,
    var bottomLeftArea: PlaneArea? = null,
    var bottomMiddleArea: PlaneArea? = null,
    var bottomRightArea: PlaneArea? = null
) {
    fun getSeatingBreakdown(): SeatingBreakdown {
        return SeatingBreakdown()
            .withIncrementedArea(topLeftArea)
            .withIncrementedArea(topMiddleArea)
            .withIncrementedArea(topRightArea)
            .withIncrementedArea(middleLeftArea)
            .withIncrementedArea(middleRightArea)
            .withIncrementedArea(bottomLeftArea)
            .withIncrementedArea(bottomMiddleArea)
            .withIncrementedArea(bottomRightArea)
    }
}

fun SeatingBreakdown.withIncrementedArea(area: PlaneArea?): SeatingBreakdown = SeatingBreakdown(
    emptyCount + if (area == PlaneArea.EMPTY) 1 else 0,
    floorCount + if (area == PlaneArea.FLOOR) 1 else 0,
    occupiedCount + if (area == PlaneArea.OCCUPIED) 1 else 0,
    wallCount + if (area == PlaneArea.WALL) 1 else 0
)


// TODO Improve architecture after caught up
class SeatingChart(val seatingArrangement: List<List<PlaneArea>>) {
    fun getSeating(row: Int, column: Int): PlaneArea {
        return seatingArrangement.getOrNull(row)?.getOrNull(column) ?: PlaneArea.WALL
    }

    fun getAdjacentSeating(row: Int, column: Int): List<PlaneArea> = listOfNotNull(
        getSeating(row - 1, column - 1),
        getSeating(row - 1, column),
        getSeating(row - 1, column + 1),
        getSeating(row, column - 1),
        getSeating(row, column + 1),
        getSeating(row + 1, column - 1),
        getSeating(row + 1, column),
        getSeating(row + 1, column + 1)
    )

    fun getAdjacentSeatingBreakdown(row: Int, column: Int): SeatingBreakdown =
        getAdjacentSeating(row, column).fold(SeatingBreakdown()) { acc, cur ->
            acc.withIncrementedArea(cur)
        }

    fun getCounts(): SeatingBreakdown = seatingArrangement.fold(SeatingBreakdown()) { acc, rows ->
        val rowAcc = rows.fold(SeatingBreakdown()) { rowAcc, area -> rowAcc.withIncrementedArea(area) }
        SeatingBreakdown(
            acc.emptyCount + rowAcc.emptyCount,
            acc.floorCount + rowAcc.floorCount,
            acc.occupiedCount + rowAcc.occupiedCount
        )
    }

    /**
     * Gets the adjacent seating expanded in the direction surrounding row column
     */
    fun getAdjacentSeatingExpandedBreakdown(row: Int, column: Int): SeatingAdjacencyBreakdown {
        val breakdown = SeatingAdjacencyBreakdown()
        val rowCount = seatingArrangement.size
        val columnCount = seatingArrangement.firstOrNull()?.size ?: -1

        var count = 1
        while (true) {
            val topRow = row - count
            val bottomRow = row + count
            val leftColumn = column - count
            val rightColumn = column + count

            if (topRow < 0 && bottomRow >= rowCount && leftColumn < 0 && rightColumn >= columnCount) {
                break
            }
            if (breakdown.topLeftArea.isFloorOrNull()) {
                breakdown.topLeftArea = getSeating(topRow, leftColumn)
            }

            if (breakdown.topMiddleArea.isFloorOrNull()) {
                breakdown.topMiddleArea = getSeating(topRow, column)
            }

            if (breakdown.topRightArea.isFloorOrNull()) {
                breakdown.topRightArea = getSeating(topRow, rightColumn)
            }

            if (breakdown.middleLeftArea.isFloorOrNull()) {
                breakdown.middleLeftArea = getSeating(row, leftColumn)
            }

            if (breakdown.middleRightArea.isFloorOrNull()) {
                breakdown.middleRightArea = getSeating(row, rightColumn)
            }

            if (breakdown.bottomLeftArea.isFloorOrNull()) {
                breakdown.bottomLeftArea = getSeating(bottomRow, leftColumn)
            }

            if (breakdown.bottomMiddleArea.isFloorOrNull()) {
                breakdown.bottomMiddleArea = getSeating(bottomRow, column)
            }

            if (breakdown.bottomRightArea.isFloorOrNull()) {
                breakdown.bottomRightArea = getSeating(bottomRow, rightColumn)
            }

            count++
        }

        return breakdown
    }

    companion object {
        fun fromLines(lines: List<String>): SeatingChart = SeatingChart(lines.map { line ->
            line.map { char -> PlaneArea.fromChar(char) }
        })
    }
}