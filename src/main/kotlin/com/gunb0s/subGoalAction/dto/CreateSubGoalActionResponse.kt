package com.gunb0s.subGoalAction.dto

import com.gunb0s.common.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CreateSubGoalActionResponse(
    val mandalartId: String,
    val mainGoalLocation: Int,
    val subGoalLocation: Int,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdDate: LocalDateTime,
    val action: String,
)
