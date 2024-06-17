package com.gunb0s.routes.mandalart.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMandalartRequest(
    val title: String,
    val goal: String
)
