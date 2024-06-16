package com.gunb0s.mandalart.repository

import com.gunb0s.mandalart.model.MainGoal
import com.gunb0s.mandalart.model.Mandalart

object MandalartRepository {
    private var count = 0
    private val mandalarts = mutableMapOf<String, Mandalart>()

    fun findById(id: String) = mandalarts[id]
    fun findMainGoalByLocation(id: String, location: Int): MainGoal? = mandalarts[id]?.mainGoals?.find { it.location == location }
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
