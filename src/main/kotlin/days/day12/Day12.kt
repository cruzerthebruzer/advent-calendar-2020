package days.day12

import util.FileReader

class Day12 : (List<String>) -> Long {
    private val ferry = Ferry()

    override fun invoke(lines: List<String>): Long {
        val actions = day12LineConverter(lines)
        ferry.applyActions(actions)
        return ferry.getManhattanDistance()
    }
}

object Day12Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day12/day12.txt"
    private val day12 = Day12()

    fun run(): Long {
        val lines = reader.read(inputPath)
        return day12(lines)
    }
}
