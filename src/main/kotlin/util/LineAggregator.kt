package util

class LineAggregator {
    fun aggregate(lines: List<String>, divider: String = ""): List<String> {
        return lines.foldIndexed(Payload(listOf(), "")) { index, acc, line ->
            when {
                line == divider -> Payload(acc.lines + acc.currentValue, "")
                lines.hasLastIndexOf(index) -> Payload(acc.lines + acc.currentValue.concatenateWithSpace(line), "")
                else -> Payload(acc.lines, acc.currentValue.concatenateWithSpace(line))
            }
        }
            .lines
    }

    private inner class Payload(
        val lines: List<String>,
        val currentValue: String
    )
}
