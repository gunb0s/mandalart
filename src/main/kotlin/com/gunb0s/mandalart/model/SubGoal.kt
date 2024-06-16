package com.gunb0s.mandalart.model

data class SubGoal(
    val location: Int,
    val goal: String,
) {
    init {
        require(location in 0..7) { "Location must be between 0 and 7" }
    }
}
