package days.day4

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

fun convertLinesToPassports(lines: List<String>, validityCheck: Map<String, String>.() -> Boolean): List<Passport?> {
    return lines.map { line ->
        val fields = line.split(' ')
        val fieldMap = mutableMapOf<String, String>()
        fields.forEach { field ->
            val (key, value) = field.split(':')
            fieldMap[key] = value
        }

        if (fieldMap.validityCheck()) {
            Passport.fromMap(fieldMap)
        } else {
            null
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

fun String?.validateEyeColor() = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").any { it == this }
fun String?.validatePassportId() = if (this == null) {
    false
} else {
    Regex("\\d{9}").matches(this)
}

val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
val allowedFields = requiredFields.plus("cid")

fun Map<String, String>.containsKeys(keys: List<String>) = keys.all { containsKey(it) }

fun Map<String, String>.isValid(requiredFields: List<String>) = requiredFields.all { key ->
    val validation = validationMap[key]
    validation?.invoke(this[key]) ?: true
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