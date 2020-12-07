package days.day4

import util.FileReader

class Day4 : (List<String>) -> List<Passport?> {
    override fun invoke(lines: List<String>): List<Passport?> =
        convertLinesToPassports(lines) { containsKeys(requiredFields) }
}

object Day4Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day4/day4.txt"
    private val day4 = Day4()

    fun run(): Int {
        val lines = reader.read(inputPath)
        val result = day4(lines)
        return result.count { it != null }
    }
}
