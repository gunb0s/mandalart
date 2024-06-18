package com.gunb0s.routes.subGoal.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateSubGoalResponse(
    val location: Int,
    val goal: String
)
