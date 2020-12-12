package days.day9

class EncodingErrorFinder(private val preamble: Int) {
    fun findError(doubleList: List<Double>): Double? {
        (preamble until doubleList.size).forEachIndexed { index, testIndex ->
            val testValue = doubleList[testIndex]
            val subList = doubleList.subList(testIndex - preamble, testIndex)
            val exists = subList.any { first ->
                subList.any { second ->
                    first + second == testValue
                }
            }
            if (!exists) {
                return testValue
            }
        }

        return null
    }
}
