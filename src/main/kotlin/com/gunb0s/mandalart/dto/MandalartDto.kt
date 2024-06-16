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
        val row: Int,
        val col: Int,
        val goal: String
    )

    companion object {
        fun fromModel(mandalart: Mandalart): MandalartDto {
            return MandalartDto(
                id = mandalart.id,
                title = mandalart.title,
                goal = mandalart.goal,
                mainGoals = mandalart.mainGoals.map {
                    MainGoalDto(
                        row = it.row,
                        col = it.col,
                        goal = it.goal
                    )
                }
            )
        }
    }
}
