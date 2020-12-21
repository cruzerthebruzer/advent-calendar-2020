package days.day11

import util.FileReader

class Day11 : (List<String>) -> SeatingBreakdown? {
    override fun invoke(lines: List<String>): SeatingBreakdown? {
        var seatingChart = SeatingChart.fromLines(lines)
        while (true) {
            val newSeating = seatingChart.seatingArrangement.mapIndexed { rowIndex, row ->
                row.mapIndexed { columnIndex, column ->
                    val adjacencyBreakdown = seatingChart.getAdjacentSeatingBreakdown(rowIndex, columnIndex)
                    when {
                        column == PlaneArea.EMPTY && adjacencyBreakdown.occupiedCount == 0 -> PlaneArea.OCCUPIED
                        column == PlaneArea.OCCUPIED && adjacencyBreakdown.occupiedCount >= 4 -> PlaneArea.EMPTY
                        else -> column
                    }
                }
            }


            if (seatingChart.seatingArrangement == newSeating) {
                return seatingChart.getCounts()
            }

            seatingChart = SeatingChart(newSeating)
        }
    }
}

object Day11Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day11/day11.txt"
    private val day11 = Day11()

    fun run(): SeatingBreakdown? {
        val lines = reader.read(inputPath)
        return day11(lines)
    }
}
