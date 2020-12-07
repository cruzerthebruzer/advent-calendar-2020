package util

fun String?.concatenateWithSpace(other: String): String = if (!this.isNullOrEmpty()) {
    "$this $other"
} else {
    other
}


fun String.hasNumberOfNumbers(number: Int) = matches("\\d{$number}".toRegex())
