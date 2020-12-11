package days.day8

import util.convertSignedStringIntegerToInteger

data class CommandPayload(
    val command: Command,
    val signedInteger: Int,
    val line: Int
)

enum class Sign {
    PLUS, MINUS;

    companion object {
        fun fromChar(char: Char) = when (char) {
            '+' -> PLUS
            '-' -> MINUS
            else -> throw Error("Unknown char $char")
        }
    }
}

enum class Command {
    ACC {
        override fun apply(currentLine: Int, currentValue: Long, value: Int): Pair<Int, Long> {
            return currentLine + 1 to currentValue + value
        }
    },
    JMP {
        override fun apply(currentLine: Int, currentValue: Long, value: Int): Pair<Int, Long> {
            return currentLine + value to currentValue
        }
    },
    NOP {
        override fun apply(currentLine: Int, currentValue: Long, value: Int): Pair<Int, Long> {
            return currentLine + 1 to currentValue
        }
    };

    abstract fun apply(currentLine: Int, currentValue: Long, value: Int): Pair<Int, Long>

    companion object {
        fun fromString(value: String) = when (value) {
            "acc" -> ACC
            "jmp" -> JMP
            "nop" -> NOP
            else -> throw Error("Unknown command: $value")
        }
    }
}

fun convertLinesToCommands(lines: List<String>): List<CommandPayload> {
    return lines.mapIndexed { index, line ->
        val (commandString, signedIntegerString) = line.split(' ')
        val integer = signedIntegerString.convertSignedStringIntegerToInteger()
        val command = Command.fromString(commandString)
        CommandPayload(command, integer, index)
    }
}

class CommandRunner {
    private var currentLine = 0
    private var currentAccumulator = 0L

    fun run(commandSet: List<CommandPayload>): Pair<Long, Boolean> {
        val ranCommands = mutableMapOf<CommandPayload, Long>()
        while (true) {
            val currentCommand = commandSet.getOrNull(currentLine) ?: return currentAccumulator to true
            val mapValue = ranCommands[currentCommand]
            if (mapValue != null) {
                return currentAccumulator to false
            } else {
                val (line, result) = currentCommand.command.apply(
                    currentLine,
                    currentAccumulator,
                    currentCommand.signedInteger
                )

                ranCommands[currentCommand] = result
                currentLine = line
                currentAccumulator = result
            }
        }
    }
}
