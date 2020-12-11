package days.day8

import util.FileReader

class Day8Part2 : (List<String>) -> Long? {
    override fun invoke(lines: List<String>): Long? {
        val commands = convertLinesToCommands(lines)
        val (unswappedAccumulator, unswappedSuccess) = CommandRunner().run(commands)

        if (!unswappedSuccess) {
            return unswappedAccumulator
        }

        for ((index, command) in commands.withIndex()) {
            val swappedCommands = commands.toMutableList()
            if (command.command == Command.JMP) {
                swappedCommands[index] = command.copy(command = Command.NOP)
            } else if (command.command == Command.NOP) {
                swappedCommands[index] = command.copy(command = Command.JMP)
            }

            val (accumulator, successfullyRan) = CommandRunner().run(swappedCommands)
            if (successfullyRan) {
                return accumulator
            }
        }

        return null
    }
}

object Day8Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day8/day8.txt"
    private val day8 = Day8Part2()

    fun run(): Long? {
        val lines = reader.read(inputPath)
        return day8(lines)
    }
}

