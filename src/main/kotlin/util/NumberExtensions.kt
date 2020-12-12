package util

fun <T : Number> T.clampMin(min: T): T = if (min.toLong() > this.toLong()) {
    min
} else {
    this
}

fun <T : Number> T.clampMax(max: T): T = if (max.toLong() < this.toLong()) {
    max
} else {
    this
}