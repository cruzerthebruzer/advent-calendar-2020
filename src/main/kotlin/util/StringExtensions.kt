package util

fun String?.concatenateWithSpace(other: String): String = if (this != null) {
    "$this $other"
} else {
    other
}


fun String.hasNumberOfNumbers(number: Int) = matches("\\d{$number}".toRegex())
