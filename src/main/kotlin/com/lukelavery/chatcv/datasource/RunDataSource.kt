package com.lukelavery.chatcv.datasource

import com.lukelavery.chatcv.model.RunModel

interface RunDataSource {

    fun createRun(threadId: String): RunModel
    fun retrieveRun(threadId: String, runId: String): RunModel
}
