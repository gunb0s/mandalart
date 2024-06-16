package com.gunb0s.mandalart.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateSubGoalResponse(
    val mandalartId: String,
    val mainGoalLocation: Int,
    val location: Int,
)
