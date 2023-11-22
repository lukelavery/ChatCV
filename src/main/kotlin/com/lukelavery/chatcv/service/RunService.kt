package com.lukelavery.chatcv.service

import com.lukelavery.chatcv.datasource.RunDataSource
import com.lukelavery.chatcv.model.RunModel
import org.springframework.stereotype.Service

@Service
class RunService(private val runDataSource: RunDataSource) {

    fun createRun(threadId: String): RunModel = runDataSource.createRun(threadId)
    fun retrieveRun(threadId: String, runId: String): RunModel = runDataSource.retrieveRun(threadId, runId)
}
