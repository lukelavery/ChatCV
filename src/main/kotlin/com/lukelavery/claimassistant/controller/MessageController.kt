package com.lukelavery.claimassistant.controller

import com.lukelavery.claimassistant.data.MessageRequestBody
import com.lukelavery.claimassistant.model.MessageModel
import com.lukelavery.claimassistant.service.MessageService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("api/threads/{threadId}/messages")
class MessageController(private val messageService: MessageService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createMessage(@PathVariable threadId: String, @RequestBody messageRequestBody: MessageRequestBody): MessageModel = messageService.createMessage(threadId, messageRequestBody)

    @GetMapping
    fun listMessages(@PathVariable threadId: String): Collection<MessageModel> = messageService.listMessages(threadId)
}