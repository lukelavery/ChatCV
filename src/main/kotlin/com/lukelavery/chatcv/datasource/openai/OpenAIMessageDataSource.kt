package com.lukelavery.chatcv.datasource.openai

import com.lukelavery.chatcv.data.MessageListDto
import com.lukelavery.chatcv.data.MessageRequestBody
import com.lukelavery.chatcv.data.MessageResponse
import com.lukelavery.chatcv.datasource.MessageDataSource
import com.lukelavery.chatcv.model.MessageModel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.io.IOException

@Repository
class OpenAIMessageDataSource(
    @Autowired private val restTemplate: RestTemplate
): MessageDataSource {

    private val logger: Logger = LoggerFactory.getLogger(OpenAIMessageDataSource::class.java)

    private fun baseURL(threadId: String): String = "https://api.openai.com/v1/threads/$threadId/messages"

    @Value("\${ai-service.openai.key}")
    private lateinit var apiKey: String

    override fun createMessage(threadId: String, messageRequestBody: MessageRequestBody): MessageModel {
        // Create headers and add the API key as a bearer token
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $apiKey")
        headers.set("OpenAI-Beta", "assistants=v1")
        headers.contentType = MediaType.APPLICATION_JSON

        // Create an entity with headers
        val entity = HttpEntity<MessageRequestBody>(messageRequestBody, headers)

        // Make the request with headers
        val response: ResponseEntity<MessageResponse> = restTemplate.exchange(
            baseURL(threadId),
            HttpMethod.POST,
            entity,
            MessageResponse::class.java
        )

        val body = response.body

            ?: throw IOException("Could not fetch thread from network.")

        body.let {
            return MessageModel(
                it.id,
                it.createdAt,
                it.threadId,
                it.role,
                it.content[0].text.value
            )
        }
    }

    override fun listMessages(threadId: String): Collection<MessageModel> {
        // Create headers and add the API key as a bearer token
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $apiKey")
        headers.set("OpenAI-Beta", "assistants=v1")
        headers.contentType = MediaType.APPLICATION_JSON

        // Create an entity with headers
        val entity = HttpEntity<String>(headers)

        // Make the request with headers
        val response: ResponseEntity<MessageListDto> = restTemplate.exchange(
            baseURL(threadId),
            HttpMethod.GET,
            entity,
            MessageListDto::class.java
        )

        val body = response.body?.data

            ?: throw IOException("Could not fetch thread from network.")

        logger.debug(body.toString())

        return body.map {
            MessageModel(
                it.id,
                it.createdAt,
                it.threadId,
                it.role,
                it.content[0].text.value
            )
        }
    }
}