package com.gunb0s.model

data class Mandalart(
    val id: String,
    val title: String,
    val goal: String,
    val mainGoals: MutableList<MainGoal> = mutableListOf()
) {
    fun addMainGoal(mainGoal: MainGoal) {
        mainGoals.add(mainGoal)
    }
}
