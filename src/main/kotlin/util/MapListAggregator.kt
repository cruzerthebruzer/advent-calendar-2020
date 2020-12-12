package util

class MapListAggregator<K, V> : MutableMap<K, List<V>> by mutableMapOf() {
    fun add(key: K, value: V): List<V>? {
        val current = get(key)
        if (current != null) {
            put(key, current.plus(value))
        } else {
            put(key, listOf(value))
        }

        return get(key)
    }
}
