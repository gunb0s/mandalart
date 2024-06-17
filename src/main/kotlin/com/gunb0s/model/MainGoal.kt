package com.gunb0s.model

data class MainGoal(
    val location: Int,
    val goal: String,
    val subGoals: MutableList<SubGoal> = mutableListOf()
) {
    fun addSubGoal(subGoal: SubGoal) {
        subGoals.add(subGoal)
    }

    init {
        require(location in 0..7) { "Location must be between 0 and 7" }
    }
}
