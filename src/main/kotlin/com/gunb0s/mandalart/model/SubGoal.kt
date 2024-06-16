package com.gunb0s.mandalart.model

import com.gunb0s.subGoalAction.model.SubGoalAction

data class SubGoal(
    val location: Int,
    val goal: String,
    val subGoalActions: MutableList<SubGoalAction> = mutableListOf()
) {
    init {
        require(location in 0..7) { "Location must be between 0 and 7" }
    }

    fun addSubGoalAction(action: SubGoalAction) {
        subGoalActions.add(action)
    }
}
