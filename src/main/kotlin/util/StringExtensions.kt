package util

import days.day8.Sign

fun String?.concatenate(other: String, seperator: String = " "): String = if (!this.isNullOrEmpty()) {
    "$this$seperator$other"
} else {
    other
}

fun String.convertSignedStringIntegerToInteger(): Int {
    val sign = Sign.fromChar(first())
    val integer = drop(1).toInt()

    return if (sign == Sign.MINUS) {
        integer * -1
    } else {
        integer
    }
}