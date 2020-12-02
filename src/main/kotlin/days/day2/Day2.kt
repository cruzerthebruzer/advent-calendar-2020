package days.day2

import util.FileReader

class Day2 : () -> Int {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day2/day2.txt"

    override fun invoke(): Int {
        val lines = reader.read(inputPath)
        return lines.sumBy { line ->
            val (min, max, requiredValue, password) = Day2PasswordPayload.fromLine(line)
            if (password.toCharArray().count { it == requiredValue } in min..max) {
                1
            } else {
                0
            }
        }
    }
}
