package days.day10

data class JoltageAdapterPayload(
    val joltage: Double,
    val oneJoltageDifferential: Double,
    val threeJoltageDifferential: Double,
)

fun findMaxJoltage(sortedDoubles: List<Double>): JoltageAdapterPayload {
    val payload = sortedDoubles.fold(
        JoltageAdapterPayload(0.0, 0.0, 0.0)
    ) { acc, joltage ->
        val differential = joltage - acc.joltage
        if (differential > 3) {
            throw Error("Invalid joltage step task failed. $differential")
        }
        JoltageAdapterPayload(
            joltage,
            acc.oneJoltageDifferential + if (differential == 1.0) 1 else 0,
            acc.threeJoltageDifferential + if (differential == 3.0) 1 else 0
        )
    }

    return payload
}