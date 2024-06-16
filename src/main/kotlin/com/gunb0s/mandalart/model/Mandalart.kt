package com.gunb0s.mandalart.model

data class Mandalart(
    val id: String,
    val title: String,
    val goal: String,
    val mainGoals: MutableList<MainGoal> = mutableListOf()
) {
    fun addMainGoal(mainGoal: MainGoal) {
        val row = mainGoal.row
        val col = mainGoal.col

        if (row == 4 && col == 4) {
            throw IllegalArgumentException("Invalid row and col: $row, $col")
        }

        if (!(row in 3..5 && col in 3..5)) {
            throw IllegalArgumentException("Invalid row and col: $row, $col")
        }

        mainGoals.add(mainGoal)
    }
}
