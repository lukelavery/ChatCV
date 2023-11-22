package com.lukelavery.claimassistant.service

import com.lukelavery.claimassistant.datasource.RunDataSource
import com.lukelavery.claimassistant.model.RunModel
import org.springframework.stereotype.Service

@Service
class RunService(private val runDataSource: RunDataSource) {

    fun createRun(threadId: String): RunModel = runDataSource.createRun(threadId)
    fun retrieveRun(threadId: String, runId: String): RunModel = runDataSource.retrieveRun(threadId, runId)
}
