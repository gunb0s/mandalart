package com.gunb0s.repository

import com.gunb0s.model.MainGoal
import com.gunb0s.model.Mandalart

object MandalartRepository {
    private var count = 0
    private val mandalarts = mutableMapOf<String, Mandalart>()

    fun findById(id: String) = mandalarts[id]

    fun findMainGoalByLocation(id: String, location: Int): MainGoal?
        = mandalarts[id]?.mainGoals?.find { it.location == location }

    fun findSubGoalByLocation(id: String, mainGoalLocation: Int, subGoalLocation: Int)
         = mandalarts[id]?.
            mainGoals?.find { it.location == mainGoalLocation }?.
            subGoals?.find { it.location == subGoalLocation }

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

    fun delete(id: String) {
        mandalarts.remove(id)
    }
}
