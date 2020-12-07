package days.day6

import util.FileReader
import util.LineAggregator
import util.MapCountAggregator

class Day6Part2 : (List<String>) -> Int {
    override fun invoke(lines: List<String>): Int = countQuestionsAllAnsweredTrue(lines)
}

object Day6Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day6/day6.txt"
    private val day6 = Day6Part2()
    private val lineAggregator = LineAggregator()

    fun run(): Int {
        val lines = reader.read(inputPath)
        return day6(lineAggregator.aggregate(lines, lineSeparator = " "))
    }
}

fun countQuestionsAllAnsweredTrue(lines: List<String>): Int {
    return lines.sumBy { line ->
        val peopleCount = line.split(" ").size
        val aggregator = MapCountAggregator<Char>()
        line.toCharArray().forEach { char ->
            aggregator.add(char)
        }

        aggregator.map.entries.sumBy { entry ->
            if (entry.value == peopleCount) 1 else 0
        }
    }
}
