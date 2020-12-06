package days.day5

import util.FileReader

class Day5 : (List<String>) -> Sequence<SeatingDetails> {
    override fun invoke(lines: List<String>): Sequence<SeatingDetails> {
        return lines.asSequence().map { line ->
            val directions = line.toCharArray().map { SeatingDirection.fromChar(it) }
            searchSeating(directions, 7, 3)
        }
    }
}

object Day5Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day5/day5.txt"
    private val day5 = Day5()

    fun run(): Int? {
        val lines = reader.read(inputPath)
        val details = day5(lines)
        return details.maxByOrNull { it.getSeatId() }?.getSeatId()
    }
}
