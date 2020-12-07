package util

class MapCountAggregator<K> {
    val map = mutableMapOf<K, Int>()

    fun add(key: K): Int {
        val value = (map[key] ?: 0) + 1
        map[key] = value
        return value
    }

    fun get(key: K) = map[key] ?: 0

    fun sum() = map.entries.sumBy { it.value }
}
