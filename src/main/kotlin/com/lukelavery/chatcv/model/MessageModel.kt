package com.lukelavery.chatcv.model

data class MessageModel (
    val id: String,
    val createdAt: Long,
    val threadId: String,
    val role: String,
    val content: String
)