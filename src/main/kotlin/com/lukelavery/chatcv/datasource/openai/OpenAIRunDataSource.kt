package com.lukelavery.chatcv.datasource.openai

import com.fasterxml.jackson.databind.ObjectMapper
import com.lukelavery.chatcv.datasource.RunDataSource
import com.lukelavery.chatcv.model.RunModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.io.IOException

@Repository
class OpenAIRunDataSource(
    @Autowired private val restTemplate: RestTemplate
): RunDataSource {

    private fun baseURL(threadId: String): String = "https://api.openai.com/v1/threads/$threadId/runs"

    @Value("\${ai-service.openai.key}")
    private lateinit var apiKey: String

    @Value("\${ai-service.openai.assistant}")
    private lateinit var assistantId: String

    override fun createRun(threadId: String): RunModel {
        // Create headers and add the API key as a bearer token
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $apiKey")
        headers.set("OpenAI-Beta", "assistants=v1")
        headers.contentType = MediaType.APPLICATION_JSON

        // Create request body
        val requestBody = mapOf("assistant_id" to assistantId)
        val requestBodyJson = ObjectMapper().writeValueAsString(requestBody)

        // Create an entity with headers
        val entity = HttpEntity<String>(requestBodyJson, headers)

        // Make the request with headers
        val response: ResponseEntity<RunModel> = restTemplate.exchange(
            baseURL(threadId),
            HttpMethod.POST,
            entity,
            RunModel::class.java
        )

        return response.body
            ?: throw IOException("Could not fetch thread from network.")
    }

    override fun retrieveRun(threadId: String, runId: String): RunModel {
        // Create headers and add the API key as a bearer token
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $apiKey")
        headers.set("OpenAI-Beta", "assistants=v1")
        headers.contentType = MediaType.APPLICATION_JSON

        // Create an entity with headers
        val entity = HttpEntity<String>(headers)

        // Make the request with headers
        val response: ResponseEntity<RunModel> = restTemplate.exchange(
            "${baseURL(threadId)}/$runId",
            HttpMethod.GET,
            entity,
            RunModel::class.java
        )

        return response.body
            ?: throw IOException("Could not fetch banks from network.")
    }

}