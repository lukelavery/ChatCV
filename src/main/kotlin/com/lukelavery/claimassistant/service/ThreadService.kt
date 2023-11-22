package com.lukelavery.claimassistant.service

import com.lukelavery.claimassistant.datasource.ThreadDataSource
import com.lukelavery.claimassistant.model.ThreadModel
import org.springframework.stereotype.Service

@Service
class ThreadService(private val threadDataSource: ThreadDataSource) {

    fun createThread(): ThreadModel = threadDataSource.createThread()
}