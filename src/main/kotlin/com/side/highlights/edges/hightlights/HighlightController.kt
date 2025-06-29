package com.side.highlights.edges.hightlights

import CreateHighlightResponse
import GetAllHighlightsResponse
import GetHighlightResponse
import CreateHighlightRequest
import UpdateHighlightRequest
import UpdateHighlightResponse
import com.side.highlights.moduels.HighlightService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class HighlightController(
    private val service: HighlightService,
) {

    @PostMapping("/highlights", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun create(
        @RequestPart file: MultipartFile,
        @ModelAttribute body: CreateHighlightRequest
    ): CreateHighlightResponse {
        return service.create(file, body)
    }

    @PutMapping("/highlights/{id}")
    fun update(@PathVariable id: String, @RequestBody body: UpdateHighlightRequest): UpdateHighlightResponse {
        return service.update(id, body)
    }

    @GetMapping("/highlights/{id}")
    fun get(@PathVariable id: String): GetHighlightResponse {
        return service.get(id)
    }

    @GetMapping("/me/highlights")
    fun getAll(): GetAllHighlightsResponse {
        return service.getAll()
    }

}