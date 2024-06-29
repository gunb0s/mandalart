package com.gunb0s.routes.mandalart.dto

import com.gunb0s.model.Mandalart
import kotlinx.serialization.Serializable

@Serializable
data class MandalartDto(
    val id: String,
    val title: String,
    val goal: String,
    val mainGoals: List<InternalMainGoalDto>
) {
    @Serializable
    data class InternalMainGoalDto(
        val location: Int,
        val goal: String,
    )

    companion object {
        fun fromModel(mandalart: Mandalart): MandalartDto {
            return MandalartDto(
                id = mandalart.id,
                title = mandalart.title,
                goal = mandalart.goal,
                mainGoals = mandalart.mainGoals.map {
                    InternalMainGoalDto(
                        location = it.location,
                        goal = it.goal,
                    )
                }
            )
        }
    }
}
