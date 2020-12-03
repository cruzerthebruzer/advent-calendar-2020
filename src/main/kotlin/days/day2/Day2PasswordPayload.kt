package days.day2

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.regex.Pattern

data class Day2PasswordPayload(
    val min: Int,
    val max: Int,
    val requiredValue: Char,
    val password: String
) {
    companion object {
        // Format is 1-3 a: abcde
        fun fromLine(line: String): Day2PasswordPayload {
            val (min, max, requiredValue, password) = line.split(Pattern.compile(": | |-"))

            return Day2PasswordPayload(
                min.toInt(),
                max.toInt(),
                requiredValue[0],
                password
            )
        }
    }
}
