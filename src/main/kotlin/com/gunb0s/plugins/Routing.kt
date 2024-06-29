package com.gunb0s.plugins

import com.gunb0s.routes.mainGoal.createMainGoalRoute
import com.gunb0s.routes.mainGoal.getMainGoalRoute
import com.gunb0s.routes.mandalart.createMandalartRoute
import com.gunb0s.routes.mandalart.deleteMandalartRoute
import com.gunb0s.routes.mandalart.getMandalartRoute
import com.gunb0s.routes.mandalart.updateMandalartRoute
import com.gunb0s.routes.subGoal.createSubGoalRoute
import com.gunb0s.routes.subGoal.getSubGoalRoute
import com.gunb0s.routes.subGoalAction.createSubGoalActionRoute
import io.ktor.server.application.Application
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.mandalartRoutes() {
    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
        route("/mandalarts") {
            createMandalartRoute()
            getMandalartRoute()
            updateMandalartRoute()
            deleteMandalartRoute()

            createMainGoalRoute()
            getMainGoalRoute()

            createSubGoalRoute()
            getSubGoalRoute()

            createSubGoalActionRoute()
        }
    }
}
