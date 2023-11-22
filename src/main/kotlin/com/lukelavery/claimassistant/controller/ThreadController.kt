package com.lukelavery.claimassistant.controller

import com.lukelavery.claimassistant.model.ThreadModel
import com.lukelavery.claimassistant.service.ThreadService
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