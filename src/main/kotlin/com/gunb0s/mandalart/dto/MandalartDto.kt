package com.gunb0s.mandalart.dto

import com.gunb0s.mandalart.model.Mandalart
import kotlinx.serialization.Serializable

@Serializable
data class MandalartDto(
    val id: String,
    val title: String,
    val goal: String,
    val mainGoals: List<MainGoalDto>
) {
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
    }

    companion object {
        fun fromModel(mandalart: Mandalart): MandalartDto {
            return MandalartDto(
                id = mandalart.id,
                title = mandalart.title,
                goal = mandalart.goal,
                mainGoals = mandalart.mainGoals.map {
                    MainGoalDto(
                        location = it.location,
                        goal = it.goal,
                        subGoals = it.subGoals.map { subGoal ->
                            MainGoalDto.SubGoalDto(
                                location = subGoal.location,
                                goal = subGoal.goal
                            )
                        }
                    )
                }
            )
        }
    }
}
