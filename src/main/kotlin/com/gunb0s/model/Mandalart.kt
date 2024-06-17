package com.gunb0s.model

data class Mandalart(
    val id: String,
    var title: String,
    var goal: String,
    val mainGoals: MutableList<MainGoal> = mutableListOf()
) {
    fun addMainGoal(mainGoal: MainGoal) {
        mainGoals.add(mainGoal)
    }
}
