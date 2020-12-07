import days.day3.Day3
import days.day3.Day3Part2Runner
import days.day4.Day4Part2Runner
import days.day4.Day4Runner

fun main() {
    runDay4()
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