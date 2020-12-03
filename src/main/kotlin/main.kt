import days.day3.Day3
import days.day3.Day3Part2
import days.day3.Day3Part2Runner

fun main() {
    Day3()()
    runDay3()
}

fun runDay3() {
    val day3 = Day3()()
    println(day3)
    val day3Part2Result = Day3Part2Runner.runDefault()
    println(day3Part2Result)
}
