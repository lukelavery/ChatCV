package com.lukelavery.claimassistant.datasource

import com.lukelavery.claimassistant.model.RunModel

interface RunDataSource {

    fun createRun(threadId: String): RunModel
    fun retrieveRun(threadId: String, runId: String): RunModel
}
