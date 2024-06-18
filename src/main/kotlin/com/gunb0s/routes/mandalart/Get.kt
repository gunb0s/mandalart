package com.gunb0s.routes.mandalart

import com.gunb0s.common.dto.ResponseDto
import com.gunb0s.routes.mandalart.dto.MandalartDto
import com.gunb0s.repository.MandalartRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getMandalartRoute() {
    get("{id?}") {
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("Missing id")
        val mandalart = MandalartRepository.findById(id)
            ?: throw IllegalArgumentException("No mandalart with id $id")

        val dto = MandalartDto.fromModel(mandalart)

        call.respond(
            HttpStatusCode.OK,
            ResponseDto(data = dto)
        )
    }
}
