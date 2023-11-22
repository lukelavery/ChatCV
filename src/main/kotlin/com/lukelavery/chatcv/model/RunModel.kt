package com.lukelavery.chatcv.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RunModel(
    val id: String,
    val createdAt: Long,

    @JsonProperty("assistant_id")
    val assistantId: String,

    @JsonProperty("thread_id")
    val threadId: String,
    var status: String,
)