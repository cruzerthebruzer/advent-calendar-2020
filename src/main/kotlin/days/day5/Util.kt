package days.day5

import kotlin.math.pow

data class SeatingDetails(val row: Int, val column: Int) {
    fun getSeatId() = row * 8 + column
}

enum class SeatingDirection {
    FORWARD, BACK, LEFT, RIGHT;

    companion object {
        fun fromChar(char: Char): SeatingDirection = when (char) {
            'F' -> FORWARD
            'B' -> BACK
            'L' -> LEFT
            'R' -> RIGHT
            else -> throw Error("Invalid Seating Direction $char")
        }
    }
}

fun searchSeating(directions: List<SeatingDirection>, forwardBackCount: Int, leftRightCount: Int): SeatingDetails {
    // First 7 are Forward/Back Next 3 are Left/Right
    val forwardBack = directions.subList(0, forwardBackCount)
    val rows = 2.toDouble().pow(forwardBackCount)
    val range = IntRange(0, rows.toInt()).toList()
    val row = getRow(range, forwardBack, 0)

    val leftRight = directions.subList(forwardBackCount, forwardBackCount + leftRightCount)
    val columns = 2.toDouble().pow(leftRightCount)
    val column = getColumn(IntRange(0, columns.toInt()).toList(), leftRight, 0)

    return SeatingDetails(row, column)
}

fun getColumn(columns: List<Int>, directions: List<SeatingDirection>, currentIndex: Int): Int {
    val currentDirection = directions.getOrNull(currentIndex) ?: return columns.first()
    return when (currentDirection) {
        SeatingDirection.LEFT -> getColumn(columns.subList(0, columns.size / 2), directions, currentIndex + 1)
        SeatingDirection.RIGHT -> getColumn(
            columns.subList(columns.size / 2, columns.size),
            directions,
            currentIndex + 1
        )
        else -> throw Error("Unexpected direction on `getColumn`: $currentDirection")
    }
}

fun getRow(rows: List<Int>, directions: List<SeatingDirection>, currentIndex: Int): Int {
    val currentDirection = directions.getOrNull(currentIndex) ?: return rows.first()
    return when (currentDirection) {
        SeatingDirection.BACK -> getRow(rows.subList(rows.size / 2, rows.size), directions, currentIndex + 1)
        SeatingDirection.FORWARD -> getRow(rows.subList(0, rows.size / 2), directions, currentIndex + 1)
        else -> throw Error("Unexpected direction on `getRow`: $currentDirection")
    }
}

fun getSeatingDirectionsFromLines(lines: List<String>): Sequence<SeatingDetails> {
    return lines.asSequence().map { line ->
        val directions = line.toCharArray().map { SeatingDirection.fromChar(it) }
        searchSeating(directions, 7, 3)
    }
}