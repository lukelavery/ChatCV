package com.lukelavery.claimassistant.service

import com.lukelavery.claimassistant.data.MessageRequestBody
import com.lukelavery.claimassistant.datasource.MessageDataSource
import com.lukelavery.claimassistant.model.MessageModel
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageDataSource: MessageDataSource) {

    fun createMessage(threadId: String, messageRequestBody: MessageRequestBody): MessageModel = messageDataSource.createMessage(threadId, messageRequestBody)
    fun listMessages(threadId: String): Collection<MessageModel> = messageDataSource.listMessages(threadId)
}