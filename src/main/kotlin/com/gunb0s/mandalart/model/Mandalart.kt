package com.gunb0s.mandalart.model

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
