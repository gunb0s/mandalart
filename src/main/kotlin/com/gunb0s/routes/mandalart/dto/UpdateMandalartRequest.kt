package com.gunb0s.routes.mandalart.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateMandalartRequest(
    val title: String? = null,
    val goal: String? = null
)
