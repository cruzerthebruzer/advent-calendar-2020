package days.day7

import util.FileReader

class Day7Part2 : (List<String>, String) -> Int {
    override fun invoke(lines: List<String>, bagToMatch: String): Int {
        val rules = parseLinesToBagRules(lines)
        return countContainersThatCanContainBag(bagToMatch, rules)
    }

    private fun countContainersThatCanContainBag(bag: String, bags: List<BagRules>): Int {
        val ruleMap = bags.associateBy { it.label }
        return countBags(bag, ruleMap)
    }

    private fun countBags(bag: String, ruleMap: Map<String, BagRules>): Int {
        val bagRules = ruleMap[bag]
        return bagRules?.rules?.sumBy { rule -> rule.count + rule.count * countBags(rule.label, ruleMap) } ?: 1
    }
}

object Day7Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day7/day7.txt"
    private val dayPart7 = Day7Part2()

    fun run(): Int {
        val lines = reader.read(inputPath)
        return dayPart7(lines, "shiny gold")
    }
}
