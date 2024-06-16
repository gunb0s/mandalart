package com.gunb0s.mandalart.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMainGoalRequest(
    val location: Int,
    val goal: String
)
