package com.gunb0s.mandalart

object MandalartRepository {
    private var count = 1
    private val mandalarts = mutableMapOf(
        "1" to Mandalart(
            id = "1",
            title = "Mandalart 1",
            goal = "Goal 1"
        )
    )

    fun find() = mandalarts.values.toList()
    fun findById(id: String) = mandalarts[id]
    fun create(title: String, goal: String): Mandalart {
        count++
        val id = count.toString()

        val mandalart = Mandalart(
            id = id,
            title = title,
            goal = goal
        )

        mandalarts[id] = mandalart

        return mandalart
    }
}
