package days.day10

import util.FileReader

class Day10 : (List<String>) -> Double? {
    override fun invoke(lines: List<String>): Double? {
        val result = parse(lines)
        return result.threeJoltageDifferential * result.oneJoltageDifferential
    }

    private fun parse(lines: List<String>): JoltageAdapterPayload {
        val doubleList = lines.map(String::toDouble)
        val sorted = doubleList.sorted()

        // Add 3 for device
        return findMaxJoltage(sorted).let {
            it.copy(
                threeJoltageDifferential = it.threeJoltageDifferential + 1,
                joltage = it.joltage + 3
            )
        }
    }
}

object Day10Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day10/day10.txt"
    private val day10 = Day10()

    fun run(): Double? {
        val lines = reader.read(inputPath)
        return day10(lines)
    }
}


