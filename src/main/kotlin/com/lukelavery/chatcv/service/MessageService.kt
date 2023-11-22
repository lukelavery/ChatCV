package com.lukelavery.chatcv.service

import com.lukelavery.chatcv.data.MessageRequestBody
import com.lukelavery.chatcv.datasource.MessageDataSource
import com.lukelavery.chatcv.model.MessageModel
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageDataSource: MessageDataSource) {

    fun createMessage(threadId: String, messageRequestBody: MessageRequestBody): MessageModel = messageDataSource.createMessage(threadId, messageRequestBody)
    fun listMessages(threadId: String): Collection<MessageModel> = messageDataSource.listMessages(threadId)
}