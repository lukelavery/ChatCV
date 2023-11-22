package com.lukelavery.chatcv.datasource

import com.lukelavery.chatcv.model.ThreadModel

interface ThreadDataSource {

    fun createThread(): ThreadModel
}