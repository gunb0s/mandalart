package com.gunb0s.routes.mandalart

import com.gunb0s.common.dto.ResponseDto
import com.gunb0s.repository.MandalartRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteMandalartRoute() {
    delete("{id}") {
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("Missing id")

        MandalartRepository.delete(id)

        call.respond(
            HttpStatusCode.NoContent,
            ResponseDto(data = null)
        )
    }
}
