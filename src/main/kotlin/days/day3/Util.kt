package days.day3

import kotlin.math.ceil

data class SledMovement(
    val down: Int,
    val right: Int,
)

data class SkiTreeState(
    val down: Int,
    val right: Int,
    val treeCollisions: Int
)

fun ski(slope: List<CharArray>, down: Int, right: Int): SkiTreeState {
    // Lines at first position, position on line at 2nd position. (y, x)
    val width = slope.first().size
    val downMoveCount = ceil(slope.size / down.toDouble()).toInt()

    return (0 until downMoveCount)
        .fold(SkiTreeState(0, 0, 0)) { (currentDown, currentRight, currentCollisions), _ ->
            val collision = slope[currentDown][currentRight].isTree()
            val updatedCollisions = if (collision) currentCollisions + 1 else currentCollisions
            SkiTreeState(currentDown + down, (currentRight + right) % width, updatedCollisions)
        }
}

fun Char.isTree() = this == '#'
