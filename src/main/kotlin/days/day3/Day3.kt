package days.day3

import util.FileReader

class Day3 : () -> Int {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day3/day3.txt"

    override fun invoke(): Int {
        val lines = reader.read(inputPath)
        val slope = lines.map { line -> line.toCharArray() }
        return ski(slope, 1, 3).treeCollisions
    }
}

//    fun doTheThing(lines: List<String>, right: Int, down: Int): Int {
//        // Lines at first position, position on line at 2nd position. (y, x)
//        val map = lines.map { line -> line.toCharArray() }
//        val width = map.first().size
//        val downMoveCount = lines.size / down.toDouble()
//
//        var treeCount = 0
//        var count = 0
//        var currentPosition = 0 to 0
//        while (count < downMoveCount) {
//            val isTree = map[currentPosition.first][currentPosition.second] == '#'
//
//            if (isTree) {
//                treeCount++
//            }
//
//
//            val newSecond = if (currentPosition.second + right >= width) {
//                currentPosition.second + right - width
//            } else {
//                currentPosition.second + right
//            }
//
//            val newFirst = if (currentPosition.first + down >= lines.size) {
//                break
//            } else {
//                currentPosition.first + down
//            }
//
//            currentPosition = newFirst to newSecond
//            count++
//        }
//
//        return treeCount
//    }
//
//    override fun invoke(): Int {
//        val lines = reader.read(inputPath)
//        val slope = lines.map { line -> line.tochar }
//        ski(lines.map { line -> line.toCharArray() }, 3, 1)
//        println(
//            doTheThing(lines, 1, 1).toDouble() *
//                    doTheThing(lines, 3, 1) *
//                    doTheThing(lines, 5, 1) *
//                    doTheThing(lines, 7, 1) *
//                    doTheThing(lines, 1, 2)
//        )
//
//        return 0
//    }
//}
//
//fun ski(slope: List<CharArray>, down: Int, right: Int): Int {
//    // Lines at first position, position on line at 2nd position. (y, x)
//    val width = slope.first().size
//    val downMoveCount = slope.size / down.toDouble()
//
//    var treeCount = 0
//    var count = 0
//    var currentPosition = 0 to 0
//    while (count < downMoveCount) {
//        val isTree = slope[currentPosition.first][currentPosition.second] == '#'
//
//        if (isTree) {
//            treeCount++
//        }
//
//
//        val newSecond = if (currentPosition.second + right >= width) {
//            currentPosition.second + right - width
//        } else {
//            currentPosition.second + right
//        }
//
//        val newFirst = if (currentPosition.first + down >= slope.size) {
//            break
//        } else {
//            currentPosition.first + down
//        }
//
//        currentPosition = newFirst to newSecond
//        count++
//    }
//
//    return treeCount
//}
//
//fun Char.isTree() = this == '*'
