package days.day2

data class Day2PasswordPayload(
    val min: Int,
    val max: Int,
    val requiredValue: Char,
    val password: String
) {
    companion object {
        // Format is 1-3 a: abcde
        fun fromLine(line: String): Day2PasswordPayload {
            val (details, value) = line.split(": ")
            val (range, requiredValue) = details.split(" ")
            val (min, max) = range.split("-").map { number -> number.toInt() }

            return Day2PasswordPayload(
                min,
                max,
                requiredValue[0],
                value
            )
        }
    }
}
