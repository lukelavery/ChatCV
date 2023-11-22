package com.lukelavery.chatcv.controller

import com.lukelavery.chatcv.model.ThreadModel
import com.lukelavery.chatcv.service.ThreadService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("api/threads")
class ThreadController(private val threadService: ThreadService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createThread(): ThreadModel = threadService.createThread()
}