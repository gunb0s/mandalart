package com.gunb0s.routes.mainGoal.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMainGoalRequest(
    val location: Int,
    val goal: String
)
