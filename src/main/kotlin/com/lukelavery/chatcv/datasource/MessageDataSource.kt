package com.lukelavery.chatcv.datasource

import com.lukelavery.chatcv.data.MessageRequestBody
import com.lukelavery.chatcv.model.MessageModel

interface MessageDataSource {

    fun createMessage(threadId: String, messageRequestBody: MessageRequestBody): MessageModel
    fun listMessages(threadId: String): Collection<MessageModel>
}