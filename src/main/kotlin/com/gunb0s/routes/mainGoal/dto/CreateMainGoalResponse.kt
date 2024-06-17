package com.gunb0s.routes.mainGoal.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMainGoalResponse(
    val mandalartId: String,
    val location: Int,
)
