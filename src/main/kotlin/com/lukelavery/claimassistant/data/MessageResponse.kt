package com.lukelavery.claimassistant.data

import com.fasterxml.jackson.annotation.JsonProperty

data class MessageResponse(
    val id: String,
    val createdAt: Long,

    @JsonProperty("thread_id")
    val threadId: String,
    val role: String,
    val content: List<ContentItem>,

    @JsonProperty("file_ids")
    val fileIds: List<String>,

    @JsonProperty("assistant_id")
    val assistantId: String?,

    @JsonProperty("run_id")
    val runId: String?,
    val metadata: Map<String, Any>
)

data class ContentItem(
    val type: String,
    val text: TextContent
)

data class TextContent(
    val value: String,
    val annotations: List<Any>
)
