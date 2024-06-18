package com.gunb0s.routes.mainGoal.dto

import com.gunb0s.model.MainGoal
import kotlinx.serialization.Serializable

@Serializable
data class MainGoalDto(
    val location: Int,
    val goal: String,
    val subGoals: List<SubGoalDto>
) {
    @Serializable
    data class SubGoalDto(
        val location: Int,
        val goal: String
    )

    companion object {
        fun fromModel(mainGoal: MainGoal): MainGoalDto {
            return MainGoalDto(
                location = mainGoal.location,
                goal = mainGoal.goal,
                subGoals = mainGoal.subGoals.map { subGoal ->
                    SubGoalDto(
                        location = subGoal.location,
                        goal = subGoal.goal
                    )
                }
            )
        }
    }
}
