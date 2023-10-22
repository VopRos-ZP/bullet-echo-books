package vopros.bulkapedia

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val numbers = mapOf(
            "0" to intArrayOf(0, 3, 3, 6, 6, 9, 9, 12, 12, 13, 13),
            "1" to intArrayOf(0, 0, 1, 1, 3, 3, 4, 4, 6, 6, 7),
        )
        val startNumbers = mapOf(
            "0" to 53,
            "1" to 6
        )
        val result = numbers.mapValues { (k, arr) ->
            arr.map { startNumbers[k]!! + it }.toIntArray()
        }
        assertArrayEquals(result["0"], intArrayOf(53, 56, 56, 59, 59, 62, 62, 65, 65, 66, 66))
    }
}