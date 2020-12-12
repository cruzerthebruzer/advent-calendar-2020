package days.day9

import util.FileReader

class Day9Part2 : (Int, List<String>) -> Pair<Double, Double>? {
    override fun invoke(preamble: Int, lines: List<String>): Pair<Double, Double>? {
        val doubleList = lines.map(String::toDouble)
        val error = EncodingErrorFinder(preamble).findError(doubleList)
        for ((firstIndex, first) in doubleList.withIndex()) {
            var sum = first
            for (secondIndex in firstIndex + 1 until doubleList.size) {
                sum += doubleList[secondIndex]

                if (sum == error) {
                    val currentList = doubleList.subList(firstIndex, secondIndex)
                    val min = currentList.minOrNull()
                    val max = currentList.maxOrNull()
                    if (min != null && max != null) {
                        return min to max
                    } else {
                        break
                    }
                } else if (sum > (error ?: 0.0)) {
                    break
                }
            }
        }

        return null
    }
}

object Day9Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day9/day9.txt"
    private val day9 = Day9Part2()

    fun run(): Double? {
        val lines = reader.read(inputPath)
        return day9(25, lines)?.let { it.first + it.second }
    }
}
