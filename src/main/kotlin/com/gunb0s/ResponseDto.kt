package com.gunb0s

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto<T>(
    val data: T? = null,
    val message: String = "Success",
    val error: String? = null
)
