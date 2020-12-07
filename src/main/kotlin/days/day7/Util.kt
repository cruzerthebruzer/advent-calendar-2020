package days.day7

data class BagRules(
    val label: String,
    val rules: List<BagRule>
)

data class BagRule(
    val label: String,
    val count: Int
)

fun parseLinesToBagRules(lines: List<String>): List<BagRules> {
    return lines.map { line ->
        val bagDetails = line.split(Regex(" bags contain |, "))
        val label = bagDetails.first()
        val bagRules = bagDetails.drop(1)
        if (bagRules.first().startsWith("no")) {
            BagRules(label, listOf())
        } else {
            BagRules(
                label,
                bagRules.map { rule ->
                    val ruleDetails = rule.split(" ")
                    val ruleLabel = ruleDetails.drop(1).filter { !it.matches(Regex("bags?\\.?")) }
                    BagRule(ruleLabel.joinToString(" "), ruleDetails.first().toInt())
                }
            )
        }
    }
}
