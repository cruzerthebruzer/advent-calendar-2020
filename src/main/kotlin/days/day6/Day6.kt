package days.day6

import util.FileReader
import util.LineAggregator

class Day6 : (List<String>) -> Int {
    override fun invoke(lines: List<String>): Int = countQuestions(lines)
}

object Day6Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day6/day6.txt"
    private val day6 = Day6()
    private val lineAggregator = LineAggregator()

    fun run(): Int {
        val lines = reader.read(inputPath)
        return day6(lineAggregator.aggregate(lines, lineSeparator = ""))
    }
}

fun countQuestions(lines: List<String>) = lines.sumBy { line ->
    val set = mutableSetOf<Int>()
    line.chars().forEach { char -> set.add(char) }
    set.size
}
