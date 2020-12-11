package days.day8

import util.FileReader

class Day8 : (List<String>) -> Long? {
    override fun invoke(lines: List<String>): Long? {
        val commands = convertLinesToCommands(lines)
        return CommandRunner().run(commands).first
    }
}

object Day8Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day8/day8.txt"
    private val day8 = Day8()

    fun run(): Long? {
        val lines = reader.read(inputPath)
        return day8(lines)
    }
}

