package days.day2

import util.FileReader

class Day2 : () -> Int {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day2/day2.txt"

    override fun invoke(): Int {
        val lines = reader.read(inputPath)
        return lines.sumBy { line ->
            val (min, max, requiredValue, password) = Day2PasswordPayload.fromLine(line)

            val sortedValue = password.toCharArray().sorted().toCharArray()
            val initialFind = sortedValue.binarySearch(requiredValue)

            var count = 1

            if (initialFind >= 0) {
                var lowerBound = initialFind - 1
                var upperBound = initialFind + 1

                while (true) {
                    if (lowerBound == -1 && upperBound == sortedValue.size) {
                        break
                    }

                    if (lowerBound >= 0) {
                        val result = sortedValue[lowerBound]
                        if (result == requiredValue) {
                            lowerBound--
                            count++
                        } else {
                            lowerBound = -1
                        }
                    }

                    if (upperBound < sortedValue.size) {
                        val result = sortedValue[upperBound]
                        if (result == requiredValue) {
                            upperBound++
                            count++
                        } else {
                            upperBound = sortedValue.size
                        }
                    }
                }

                if (count in min..max) 1 else 0
            } else {
                if (min == 0) 1 else 0
            }
        }
    }
}
