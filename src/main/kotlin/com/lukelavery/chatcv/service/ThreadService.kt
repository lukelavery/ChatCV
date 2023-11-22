package com.lukelavery.chatcv.service

import com.lukelavery.chatcv.datasource.ThreadDataSource
import com.lukelavery.chatcv.model.ThreadModel
import org.springframework.stereotype.Service

@Service
class ThreadService(private val threadDataSource: ThreadDataSource) {

    fun createThread(): ThreadModel = threadDataSource.createThread()
}