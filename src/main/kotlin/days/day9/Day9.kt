package days.day9

import util.FileReader

class Day9 : (Int, List<String>) -> Double? {
    override fun invoke(preamble: Int, lines: List<String>): Double? {
        return EncodingErrorFinder(preamble).findError(lines.map(String::toDouble))
    }
}

object Day9Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day9/day9.txt"
    private val day9 = Day9()

    fun run(): Double? {
        val lines = reader.read(inputPath)
        return day9(25, lines)
    }
}

