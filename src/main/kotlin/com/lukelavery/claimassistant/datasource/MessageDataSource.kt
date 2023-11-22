package com.lukelavery.claimassistant.datasource

import com.lukelavery.claimassistant.data.MessageRequestBody
import com.lukelavery.claimassistant.model.MessageModel

interface MessageDataSource {

    fun createMessage(threadId: String, messageRequestBody: MessageRequestBody): MessageModel
    fun listMessages(threadId: String): Collection<MessageModel>
}