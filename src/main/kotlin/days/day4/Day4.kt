package days.day4

import util.FileReader
import util.isLastIndex

class Day4 : (List<String>) -> List<Passport?> {
    override fun invoke(lines: List<String>): List<Passport?> = convertLines(lines)
}

object Day4Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day4/day4.txt"
    private val day4 = Day4()

    fun run(): Int {
        val lines = reader.read(inputPath)
        val result = day4(lines)
        return result.count { it != null }
    }
}

class Day4Part2 : (List<String>) -> List<Passport?> {
    override fun invoke(lines: List<String>): List<Passport?> {
        return convertLines(lines)
    }
}

object Day4Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day4/day4.txt"
    private val day4Part2 = Day4Part2()

    fun run(): Int {
        val lines = reader.read(inputPath)
        val result = day4Part2(lines)
        return result.count { it != null }
    }
}

// 216
fun convertLines(lines: List<String>): List<Passport?> {
    return lines.foldIndexed(ReductionPayload(null, listOf())) { index, acc, line ->
        val isLastLine = lines.isLastIndex(index)
        if (line.trim().isEmpty() || isLastLine) {
            val fields = if (isLastLine) {
                (acc.currentValue!! + " " + line).split(' ')
            } else {
                acc.currentValue?.split(' ')
            }
            if (fields != null) {
                val fieldMap = mutableMapOf<String, String>()
                fields.forEach { field ->
                    val (key, value) = field.split(':')
                    fieldMap[key] = value
                }

                if (fieldMap.isValid()) {
                    ReductionPayload(null, acc.currentPassports.plus(Passport.fromMap(fieldMap)))
                } else {
                    ReductionPayload(null, acc.currentPassports.plus(null as Passport?))
                }
            } else {
                /**
                 * If current value is null there's no previous input
                 */
                acc
            }
        } else {
            if (acc.currentValue.isNullOrEmpty()) {
                ReductionPayload(line, acc.currentPassports)
            } else {
                ReductionPayload(acc.currentValue + " " + line, acc.currentPassports)
            }
        }
    }.currentPassports
}

data class Passport(
    val birthYear: Int,
    val issueYear: Int,
    val expirationYear: Int,
    val height: String,
    val hairColor: String,
    val eyeColor: String,
    val id: String,
    val countryId: String?
) {
    companion object {
        fun fromMap(map: Map<String, String>): Passport {
            return Passport(
                birthYear = map["byr"]!!.toInt(),
                issueYear = map["iyr"]!!.toInt(),
                expirationYear = map["eyr"]!!.toInt(),
                height = map["hgt"]!!,
                hairColor = map["hcl"]!!,
                eyeColor = map["ecl"]!!,
                id = map["pid"]!!,
                countryId = map["cid"]
            )
        }
    }
}

fun String?.validateYear(min: Int, max: Int): Boolean = if (this == null) {
    false
} else {
    val year = this.toInt()
    year in min..max
}

fun String?.validateBirthYear() = validateYear(1920, 2002)
fun String?.validateIssueYear() = validateYear(2010, 2020)
fun String?.validateExpirationYear() = validateYear(2020, 2030)


fun String?.validateHeight() = if (this == null) {
    false
} else {
    Regex("([1]([5-8][0-9]|[9][0-3]))cm|([5][9]|([6][0-9]|[7][0-6]))in").matches(this)
}

fun String?.validateHairColor() = if (this == null) {
    false
} else {
    Regex("#[0-9|a-f]{6}").matches(this)
}

fun String?.validateEyeColor() = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").any { it === this }

fun String?.validatePassportId() = if (this == null) {
    false
} else {
    Regex("\\d{9}").matches(this)
}


val validationMap = mapOf(
    "byr" to String?::validateBirthYear,
    "iyr" to String?::validateIssueYear,
    "eyr" to String?::validateExpirationYear,
    "hgt" to String?::validateHeight,
    "hcl" to String?::validateHairColor,
    "ecl" to String?::validateEyeColor,
    "pid" to String?::validatePassportId
)

data class ReductionPayload(
    val currentValue: String?,
    val currentPassports: List<Passport?>
)

val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
val allowedFields = requiredFields.plus("cid")

fun Map<String, String>.containsKeys() = requiredFields.all { containsKey(it) }

fun Map<String, String>.isValid() = requiredFields.all { key ->
    val validation = validationMap[key]
    validation?.invoke(this[key]) ?: true
}