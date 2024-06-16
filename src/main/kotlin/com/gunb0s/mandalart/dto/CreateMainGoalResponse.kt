package com.gunb0s.mandalart.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMainGoalResponse(
    val mandalartId: String,
    val row: Int,
    val col: Int,
)
