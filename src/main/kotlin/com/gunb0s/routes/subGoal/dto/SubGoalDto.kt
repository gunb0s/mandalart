package com.gunb0s.routes.subGoal.dto

import com.gunb0s.common.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SubGoalDto(
    val location: Int,
    val goal: String,
    val subGoalActions: List<SubGoalActionDto>
) {
    @Serializable
    data class SubGoalActionDto(
        @Serializable(with = LocalDateTimeSerializer::class)
        val createdDate: LocalDateTime,
        val action: String,
    )

    companion object {
        fun fromModel(subGoal: com.gunb0s.model.SubGoal): SubGoalDto {
            return SubGoalDto(
                subGoal.location,
                subGoal.goal,
                subGoal.subGoalActions.map {
                    SubGoalActionDto(
                        it.createdDate,
                        it.action
                    )
                }
            )
        }
    }
}
