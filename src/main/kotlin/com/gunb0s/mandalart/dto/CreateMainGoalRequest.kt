package com.gunb0s.mandalart.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMainGoalRequest(
    val row: Int,
    val col: Int,
    val goal: String
)
