package com.gunb0s

import com.gunb0s.mandalart.dto.CreateMainGoalRequest
import com.gunb0s.mandalart.dto.CreateMandalartRequest
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun getMandalartById() = testApplication {
        val response = client.get("/mandalarts/1")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("json", response.contentType()?.contentSubtype)
    }

    @Test
    fun createMandalart() = testApplication {
        val client = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/mandalarts") {
            contentType(ContentType.Application.Json)
            setBody(CreateMandalartRequest(
                title = "My mandalart",
                goal = "My goal"
            ))
        }

        assertEquals(HttpStatusCode.Created, response.status)
    }

    @Test
    fun createMainGoal() = testApplication {
        val client = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/mandalarts/1/main-goals") {
            contentType(ContentType.Application.Json)
            setBody(
                CreateMainGoalRequest(
                    row = 3,
                    col = 5,
                    goal = "My main goal"
                )
            )
        }

        assertEquals(HttpStatusCode.Created, response.status)
    }
}
