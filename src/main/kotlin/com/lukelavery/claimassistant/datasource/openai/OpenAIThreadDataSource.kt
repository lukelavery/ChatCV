package com.lukelavery.claimassistant.datasource.openai

import com.lukelavery.claimassistant.datasource.ThreadDataSource
import com.lukelavery.claimassistant.model.ThreadModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.io.IOException

@Repository
class OpenAIThreadDataSource(
    @Autowired private val restTemplate: RestTemplate
): ThreadDataSource {

    private val baseURL = "https://api.openai.com/v1/threads"

    @Value("\${ai-service.openai.key}")
    private lateinit var apiKey: String

    override fun createThread(): ThreadModel {
        // Create headers and add the API key as a bearer token
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $apiKey")
        // Add the OpenAI-Beta custom header
        headers.set("OpenAI-Beta", "assistants=v1")

        // Create an entity with headers
        val entity = HttpEntity<String>(headers)

        // Make the request with headers
        val response: ResponseEntity<ThreadModel> = restTemplate.exchange(
            baseURL,
            HttpMethod.POST,
            entity,
            ThreadModel::class.java
        )

        return response.body
            ?: throw IOException("Could not fetch thread from network.")
    }
}