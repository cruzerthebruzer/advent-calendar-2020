package days.day7

import util.FileReader

class Day7 : (List<String>, String) -> Int {
    override fun invoke(lines: List<String>, bagToMatch: String): Int {
        val rules = parseLinesToBagRules(lines)
        return countContainersThatCanContainBag(bagToMatch, rules)
    }

    private fun countContainersThatCanContainBag(bag: String, bags: List<BagRules>): Int {
        val ruleMap = bags.associateBy { it.label }.filter { it.key != bag }
        return ruleMap.count { entry -> findMatchingRule(bag, entry.value, ruleMap) }
    }

    private fun findMatchingRule(bag: String, rules: BagRules, ruleMap: Map<String, BagRules>): Boolean =
        rules.rules.any { rule ->
            val r = ruleMap[rule.label]
            rule.label == bag || (r != null && findMatchingRule(bag, r, ruleMap))
        }
}

object Day7Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day7/day7.txt"
    private val day7 = Day7()

    fun run(): Int {
        val lines = reader.read(inputPath)
        return day7(lines, "shiny gold")
    }
}
