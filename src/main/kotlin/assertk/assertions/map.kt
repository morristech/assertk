package assertk.assertions

import assertk.Assert
import assertk.assertions.support.expected
import assertk.assertions.support.show

/**
 * Asserts the map has the same size as the expected map.
 */
fun <T : Map<*, *>> Assert<T>.hasSameSizeAs(other: Map<*, *>) {
    val actualSize = actual.size
    val otherSize = other.size
    if (actualSize == otherSize) return
    expected("to have same size as:${show(other)} ($otherSize) but was size:($actualSize)")
}

/**
 * Asserts the map contains the expected key-value pair.
 * @see [doesNotContain]
 */
@JvmName("mapContains")
fun <K, V> Assert<Map<K, V>>.contains(key : K, value : V) {
    if (actual[key] == value) {
        return
    }
    expected("to contain:${show(mapOf(key to value))} but was:${show(actual)}")
}

/**
 * Asserts the map contains the expected key-value pair.
 * @see [doesNotContain]
 */
@JvmName("mapContains")
fun <K, V> Assert<Map<K, V>>.contains(element: Pair<K, V>) {
    contains(element.first, element.second)
}

/**
 * Asserts the map contains all the expected elements. The map may also contain additional elements.
 * @see [containsNone]
 * @see [containsExactly]
 */
@JvmName("mapContainsAll")
fun <K, V> Assert<Map<K, V>>.containsAll(vararg elements: Pair<K, V>) {
    if (elements.all { (k, v) -> actual[k] == v }) return
    expected("to contain:${show(mapOf(*elements))} but was:${show(actual)}")
}

/**
 * Asserts the map does not contain the expected key-value pair.
 * @see [contains]
 */
@JvmName("mapDoesNotContain")
fun <K, V> Assert<Map<K, V>>.doesNotContain(key: K, value: V) {
    if (actual[key] != value) {
        return
    }
    expected("to not contain:${show(mapOf(key to value))} but was:${show(actual)}")
}

/**
 * Asserts the map does not contain the expected key-value pair.
 * @see [contains]
 */
@JvmName("mapDoesNotContain")
fun <K, V> Assert<Map<K, V>>.doesNotContain(element: Pair<K, V>) {
    doesNotContain(element.first, element.second)
}

/**
 * Asserts the map does not contain any of the expected elements.
 * @see [containsAll]
 */
@JvmName("mapContainsNone")
fun <K, V> Assert<Map<K, V>>.containsNone(vararg elements: Pair<K, V>) {
    if (elements.all { (k, v) -> actual[k] != v }) return
    expected("to not contain:${show(mapOf(*elements))} but was:${show(actual)}")
}

/**
 * Asserts the map contains exactly the expected elements. There must not be any extra elements.
 * @see [containsAll]
 */
@JvmName("mapContainsExactly")
fun <K, V> Assert<Map<K, V>>.containsExactly(vararg elements: Pair<K, V>) {
    if (actual.size == elements.size && elements.all { (k, v) -> actual[k] == v }) return
    expected("to contain exactly:${show(mapOf(*elements))} but was:${show(actual)}")
}
