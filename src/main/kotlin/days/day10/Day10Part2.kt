package days.day10

import util.FileReader
import util.MapListAggregator
import util.clampMax

class Day10Part2 : (List<String>) -> Double? {
    private val countRegistrar = mutableMapOf<Double, Double>()

    override fun invoke(lines: List<String>): Double {
        return parse(lines)
    }

    private fun parse(lines: List<String>): Double {
        val doubleLines = lines.map(String::toDouble)
        val sorted = doubleLines.sorted()
        val maxJoltage = findMaxJoltage(sorted).joltage
        return count(sorted, maxJoltage)
    }

    private fun count(lines: List<Double>, maxJoltage: Double): Double {
        val mapAggregator = MapListAggregator<Double, Double>()

        val firstSublist = getSublist(-1, lines)
        firstSublist.forEach { possibleFirst ->
            if (possibleFirst.isValidDifferential()) {
                mapAggregator.add(0.0, possibleFirst)
            }
        }

        lines.forEachIndexed { index, line ->
            val subList = getSublist(index, lines)
            subList.forEach { possibleStep ->
                if ((possibleStep - line).isValidDifferential()) {
                    mapAggregator.add(line, possibleStep)
                }
            }
        }

        return mapAggregator[0.0]?.sumByDouble { starter ->
            aggregate(mapAggregator, starter, maxJoltage)
        } ?: 0.0
    }


    private fun aggregate(map: Map<Double, List<Double>>, currentValue: Double, maxJoltage: Double): Double {
        val nextSteps = map[currentValue]
        if (countRegistrar[currentValue] != null) {
            return countRegistrar[currentValue]!!
        }

        val result = nextSteps?.sumByDouble { nextStep ->
            if (nextStep == maxJoltage) {
                1.0
            } else {
                aggregate(map, nextStep, maxJoltage)
            }
        } ?: 0.0

        countRegistrar[currentValue] = result
        return result
    }

    private fun getSublist(index: Int, lines: List<Double>): List<Double> {
        val startNextIndex = index + 1
        val endNextIndex = (startNextIndex + 3).clampMax(lines.size)
        return lines.subList(startNextIndex, endNextIndex)
    }


    private fun Double.isValidDifferential() = this in 0.0..3.0
}

object Day10Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day10/day10.txt"
    private val day10 = Day10Part2()

    fun run(): Double? {
        val lines = reader.read(inputPath)
        return day10(lines)
    }
}
