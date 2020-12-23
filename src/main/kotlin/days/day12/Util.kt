package days.day12

import kotlin.math.abs
import kotlin.math.absoluteValue

sealed class FerryNavigationAction(val magnitude: Long) {
    sealed class CardinalDirection(magnitude: Long) : FerryNavigationAction(magnitude) {
        class North(magnitude: Long) : CardinalDirection(magnitude)
        class South(magnitude: Long) : CardinalDirection(magnitude)
        class West(magnitude: Long) : CardinalDirection(magnitude)
        class East(magnitude: Long) : CardinalDirection(magnitude)
    }

    sealed class TurnDirection(magnitude: Long) : FerryNavigationAction(magnitude) {
        class Left(magnitude: Long) : TurnDirection(magnitude)
        class Right(magnitude: Long) : TurnDirection(magnitude)
    }

    class Forward(magnitude: Long) : FerryNavigationAction(magnitude)

    companion object {
        // String format F10, N3, R90 etc.
        fun fromString(value: String): FerryNavigationAction {
            val (action, magnitude) = value.first() to value.toCharArray().drop(1).joinToString("").toLong()
            return when (action) {
                'L' -> TurnDirection.Left(magnitude)
                'R' -> TurnDirection.Right(magnitude)
                'N' -> CardinalDirection.North(magnitude)
                'S' -> CardinalDirection.South(magnitude)
                'W' -> CardinalDirection.West(magnitude)
                'E' -> CardinalDirection.East(magnitude)
                'F' -> Forward(magnitude)
                else -> throw Error("Unexpected argument $value for `FerryNavigationAction`")
            }
        }
    }
}

class Ferry {
    var horizontalLocation: Long = 0
        private set
    var verticalLocation: Long = 0
        private set

    // Default East at 0
    var direction: Double = 0.0
        private set

    fun applyActions(actions: List<FerryNavigationAction>) = actions.map(::applyAction)

    fun getManhattanDistance() = horizontalLocation.absoluteValue + verticalLocation.absoluteValue

    fun applyAction(action: FerryNavigationAction) {
        when (action) {
            is FerryNavigationAction.CardinalDirection -> handleCardinalDirection(action)
            is FerryNavigationAction.TurnDirection -> handleTurnDirection(action)
            is FerryNavigationAction.Forward -> handleForward(action)
        }
    }

    private fun normalizeDirectionForRadians(direction: Double) = (direction % 360).let { modded ->
        val positiveValue = (modded + 360) % 360
        if (positiveValue > 180) {
            positiveValue - 360
        } else {
            positiveValue
        }
    }

    fun clampDirection(direction: Double): Double = when {
        direction >= 0 -> direction % 360
        direction < 0 -> 360 - abs(direction % 360)
        else -> throw Error("Broken number")
    }

    private fun getXAxisNormalizedDirection(): Double = clampDirection(direction).let { normalized ->
        println("Direction: $direction clamped: $normalized")
        when {
            normalized >= 0 && normalized < 90 -> normalized
            normalized >= 90 && normalized < 180 -> 180 - normalized
            normalized >= 180 && normalized < 270 -> normalized - 180
            normalized >= 270 && normalized < 360 -> 360 - normalized
            else -> throw Error("Angle isn't appropriately normalized: $normalized")
        }
    }


    private fun handleCardinalDirection(action: FerryNavigationAction.CardinalDirection) {
        when (action) {
            is FerryNavigationAction.CardinalDirection.North -> verticalLocation += action.magnitude
            is FerryNavigationAction.CardinalDirection.South -> verticalLocation -= action.magnitude
            is FerryNavigationAction.CardinalDirection.East -> horizontalLocation += action.magnitude
            is FerryNavigationAction.CardinalDirection.West -> horizontalLocation -= action.magnitude
        }
    }

    private fun handleTurnDirection(action: FerryNavigationAction.TurnDirection) {
        direction = when (action) {
            is FerryNavigationAction.TurnDirection.Left -> direction + action.magnitude
            is FerryNavigationAction.TurnDirection.Right -> direction - action.magnitude
        }
    }

    private fun handleForward(action: FerryNavigationAction.Forward) {
        // TODO Fix this to not be hard coded degrees -- asin/acos expecting -1 to 1 rather than radians or degrees?
        // val radians = Math.toRadians(getXAxisNormalizedDirection() * Math.PI / 180)
        // val verticalChange = action.magnitude * asin(radians).toLong()
        // val horizontalChange = action.magnitude * acos(radians).toLong()

        val normalized = clampDirection(direction)
        println(normalized)
        when (normalized) {
            0.0 -> handleCardinalDirection(FerryNavigationAction.CardinalDirection.East(action.magnitude))
            90.0 -> handleCardinalDirection(FerryNavigationAction.CardinalDirection.North(action.magnitude))
            180.0 -> handleCardinalDirection(FerryNavigationAction.CardinalDirection.West(action.magnitude))
            270.0 -> handleCardinalDirection(FerryNavigationAction.CardinalDirection.South(action.magnitude))
        }
    }
}

fun day12LineConverter(lines: List<String>) = lines.map(FerryNavigationAction::fromString)