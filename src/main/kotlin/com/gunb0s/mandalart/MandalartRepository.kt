package com.gunb0s.mandalart

object MandalartRepository {
    private val mandalarts = mutableMapOf(
        "1" to Mandalart("1", "Mandalart 1"),
        "2" to Mandalart("2", "Mandalart 2"),
        "3" to Mandalart("3", "Mandalart 3"),
    )

    fun find() = mandalarts.values.toList()
    fun findById(id: String) = mandalarts[id]
}
