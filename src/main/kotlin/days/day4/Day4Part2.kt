package days.day4

import util.FileReader

class Day4Part2 : (List<String>) -> List<Passport?> {
    override fun invoke(lines: List<String>): List<Passport?> {
        return convertLinesToPassports(lines) { isValid(requiredFields) }
    }
}

object Day4Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day4/day4.txt"
    private val day4Part2 = Day4Part2()

    fun run(): Int {
        val lines = reader.read(inputPath)
        val result = day4Part2(lines)
        return result.count { it != null }
    }
}