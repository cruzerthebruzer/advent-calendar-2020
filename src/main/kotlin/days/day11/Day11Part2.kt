package days.day11

import util.FileReader

class Day11Part2 : (List<String>) -> SeatingBreakdown? {
    override fun invoke(lines: List<String>): SeatingBreakdown? {
        var seatingChart = SeatingChart.fromLines(lines)
        while (true) {
            val newSeating = seatingChart.seatingArrangement.mapIndexed { rowIndex, row ->
                row.mapIndexed { columnIndex, column ->
                    val adjacencyBreakdown =
                        seatingChart.getAdjacentSeatingExpandedBreakdown(rowIndex, columnIndex).getSeatingBreakdown()
                    when {
                        column == PlaneArea.EMPTY && adjacencyBreakdown.occupiedCount == 0 -> PlaneArea.OCCUPIED
                        column == PlaneArea.OCCUPIED && adjacencyBreakdown.occupiedCount >= 5 -> PlaneArea.EMPTY
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

object Day11Part2Runner {
    private val reader = FileReader()
    private val inputPath = "src/main/resources/day11/day11.txt"
    private val day11Part2 = Day11Part2()

    fun run(): SeatingBreakdown? {
        val lines = reader.read(inputPath)
        return day11Part2(lines)
    }
}
