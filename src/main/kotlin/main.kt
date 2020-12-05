import days.day3.Day3
import days.day3.Day3Part2Runner
import days.day4.Day4Runner

fun main() {
    println(Day4Runner.run())
}

fun runDay3() {
    val day3 = Day3()()
    println(day3)
    val day3Part2Result = Day3Part2Runner.runDefault()
    println(day3Part2Result)
}
