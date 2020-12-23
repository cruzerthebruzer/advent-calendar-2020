import days.day10.Day10Part2Runner
import days.day10.Day10Runner
import days.day11.Day11Part2Runner
import days.day11.Day11Runner
import days.day12.Day12Runner
import days.day3.Day3
import days.day3.Day3Part2Runner
import days.day4.Day4Part2Runner
import days.day4.Day4Runner
import days.day6.Day6Part2Runner
import days.day6.Day6Runner
import days.day7.Day7Part2Runner
import days.day7.Day7Runner
import days.day8.Day8Part2Runner
import days.day8.Day8Runner
import days.day9.Day9Part2Runner
import days.day9.Day9Runner

fun main() {
    runDay12()
}

fun runDay3() {
    val day3 = Day3()()
    println(day3)
    val day3Part2Result = Day3Part2Runner.runDefault()
    println(day3Part2Result)
}

fun runDay4() {
    val day4 = Day4Runner.run()
    println(day4)
    val day5 = Day4Part2Runner.run()
    println(day5)
}

fun runDay6() {
    val day6 = Day6Runner.run()
    println(day6)
    val day6Part2 = Day6Part2Runner.run()
    println(day6Part2)
}

fun runDay7() {
    val day7 = Day7Runner.run()
    println(day7)
    val day7Part2 = Day7Part2Runner.run()
    println(day7Part2)
}

fun runDay8() {
    val day8 = Day8Runner.run()
    println(day8)
    val day8Part2 = Day8Part2Runner.run()
    println(day8Part2)
}

fun runDay9() {
    val day9 = Day9Runner.run()
    println(day9)
    val day9Part2 = Day9Part2Runner.run()
    println(day9Part2)
}

fun runDay10() {
    val day10 = Day10Runner.run()
    println(day10)
    val day10Part2 = Day10Part2Runner.run()
    println(day10Part2)
}

fun runDay11() {
    val day11 = Day11Runner.run()
    println(day11)
    val day11Part2 = Day11Part2Runner.run()
    println(day11Part2)
}

fun runDay12() {
    val day12 = Day12Runner.run()
    println(day12)
}