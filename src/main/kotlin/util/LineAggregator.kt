package util

class LineAggregator {
    fun aggregate(lines: List<String>, divider: String = "", lineSeparator: String = " "): List<String> {
        return lines.foldIndexed(Payload(listOf(), "")) { index, acc, line ->
            when {
                line == divider -> Payload(acc.lines + acc.currentValue, "")
                lines.hasLastIndexOf(index) -> Payload(
                    acc.lines + acc.currentValue.concatenate(line, seperator = lineSeparator),
                    ""
                )
                else -> Payload(acc.lines, acc.currentValue.concatenate(line, seperator = lineSeparator))
            }
        }
            .lines
    }

    private inner class Payload(
        val lines: List<String>,
        val currentValue: String
    )
}
