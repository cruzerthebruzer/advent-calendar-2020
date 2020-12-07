package util

fun String?.concatenate(other: String, seperator: String = " "): String = if (!this.isNullOrEmpty()) {
    "$this$seperator$other"
} else {
    other
}


fun String.hasNumberOfNumbers(number: Int) = matches("\\d{$number}".toRegex())
