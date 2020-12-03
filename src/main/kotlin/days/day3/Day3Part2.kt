package days.day3

import util.FileReader

class Day3Part2 : (List<SledMovement>) -> Long {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day3/day3.txt"

    override fun invoke(values: List<SledMovement>): Long {
        val lines = reader.read(inputPath)
        val slope = lines.map { line -> line.toCharArray() }
        return values.fold(1) { acc, movement -> acc * ski(slope, movement.down, movement.right).treeCollisions }
    }
}

object Day3Part2Runner {
    fun runDefault(): Long = Day3Part2().invoke(
        listOf(
            SledMovement(1, 1),
            SledMovement(1, 3),
            SledMovement(1, 5),
            SledMovement(1, 7),
            SledMovement(2, 1),
        )
    )
}
