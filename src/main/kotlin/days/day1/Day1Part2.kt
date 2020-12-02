package days.day1

import util.FileReader

class Day1Part2 : () -> Int? {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day1/day1.txt"

    override fun invoke(): Int? {
        val lines = reader.read(inputPath).map { it.toInt() }
        val result = matchSum(listOf(), 2020, lines, lines, lines)
        return result?.reduce { acc, number -> acc * number }
    }

    private fun matchSum(currentValues: List<Int>, match: Int, vararg numbers: List<Int>): List<Int>? {
        if (numbers.isEmpty()) {
            return currentValues
        }

        val numberList = numbers.first()
        numberList.forEach { number ->
            val values = currentValues.plus(number)
            val result = if (numbers.isNotEmpty()) {
                matchSum(values, match, *numbers.drop(1).toTypedArray())
            } else {
                values
            }

            if (result?.sum() == match) {
                return result
            }
        }

        return null
    }
}
