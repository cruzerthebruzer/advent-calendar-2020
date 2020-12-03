package days.day3

import util.FileReader

class Day3 : () -> Int {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day3/day3.txt"

    override fun invoke(): Int {
        val lines = reader.read(inputPath)
        val slope = lines.map { line -> line.toCharArray() }
        return ski(slope, 1, 3).treeCollisions
    }
}
