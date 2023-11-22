package com.lukelavery.claimassistant.datasource

import com.lukelavery.claimassistant.model.ThreadModel

interface ThreadDataSource {

    fun createThread(): ThreadModel
}