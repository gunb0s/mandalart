package com.gunb0s.routes.mandalart

import com.gunb0s.common.dto.ResponseDto
import com.gunb0s.repository.MandalartRepository
import com.gunb0s.routes.mandalart.dto.UpdateMandalartRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.updateMandalartRoute() {
    patch("{id}") {
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("Missing id")

        val mandalart = MandalartRepository.findById(id)
            ?: throw IllegalArgumentException("No mandalart with id $id")

        val (title, goal) = call.receive<UpdateMandalartRequest>()

        mandalart.title = title ?: mandalart.title
        mandalart.goal = goal ?: mandalart.goal

        call.respond(
            HttpStatusCode.NoContent,
            ResponseDto(data = null)
        )
    }
}
