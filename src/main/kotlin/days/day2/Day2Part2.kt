package days.day2

import util.FileReader

class Day2Part2 : () -> Int {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day2/day2.txt"

    override fun invoke(): Int {
        val lines = reader.read(inputPath)
        return lines.sumBy { line ->
            val (min, max, requiredValue, password) = Day2PasswordPayload.fromLine(line)

            val passwordChars = password.toCharArray()
            val firstValue = passwordChars[min - 1] == requiredValue
            val secondValue = passwordChars[max - 1] == requiredValue

            if (firstValue.xor(secondValue)) {
                1
            } else {
                0
            }
        }
    }
}
