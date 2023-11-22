package com.lukelavery.claimassistant.controller

import com.lukelavery.claimassistant.model.RunModel
import com.lukelavery.claimassistant.service.RunService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("api/threads/{threadId}/runs")
class RunController(private val runService: RunService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRun(@PathVariable threadId: String): RunModel = runService.createRun(threadId)

    @GetMapping("/{runId}")
    fun retrieveRun(@PathVariable threadId: String, @PathVariable runId: String): RunModel = runService.retrieveRun(threadId, runId)
}