package days.day1

import util.FileReader

class Day1 : () -> Int? {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day1/day1.txt"

    override fun invoke(): Int? {
        val lines = reader.read(inputPath).map { it.toInt() }
        val result = getMultipleOf2020Sum(lines)
        println(result)
        return if (result != null) result.first * result.second else null
    }

    private fun getMultipleOf2020Sum(numbers: List<Int>): Pair<Int, Int>? {
        val sorted = numbers.sorted()
        val (evens, odds) = sorted.partition { it % 2 == 0 }

        val evensMatch = matchSum(evens, 2020)
        if (evensMatch != null) {
            return evensMatch
        }

        val oddsMatch = matchSum(odds, 2020)
        if (oddsMatch != null) {
            return oddsMatch
        }

        return null
    }

    private fun matchSum(numbers: List<Int>, match: Int): Pair<Int, Int>? {
        for (number in numbers) {
            val result = numbers.binarySearch(match - number)
            return if (result >= 0) number to numbers[result] else continue
        }

        return null
    }
}
