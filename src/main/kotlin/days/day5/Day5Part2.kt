package days.day5

import util.FileReader
import kotlin.math.pow


class Day5Part2 : (List<String>) -> List<Int> {
    override fun invoke(lines: List<String>): List<Int> {
        return getEmptySeats(getSeatingDirectionsFromLines(lines), 7, 3)
    }
}

object Day5Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day5/day5.txt"
    private val day5 = Day5Part2()

    fun run(): List<Int> {
        val lines = reader.read(inputPath)
        return day5(lines)
    }
}

fun getEmptySeats(seatingDetails: Sequence<SeatingDetails>, forwardBackCount: Int, leftRightCount: Int): List<Int> {
    val totalSeats = 2.toDouble().pow(forwardBackCount + leftRightCount)
    val seatList = IntRange(0, totalSeats.toInt()).toList()
    val sorted = seatingDetails.sortedBy { it.getSeatId() }.map { it.getSeatId() }
    val first = sorted.first()
    val last = sorted.last()
    val withoutFrontBack = seatList.slice(IntRange(first, last))

    return withoutFrontBack.filter { !sorted.contains(it) }
}