package com.gunb0s.common.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto<T>(
    val data: T? = null,
    val message: String = "Success",
    val error: String? = null
)
