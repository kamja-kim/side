package com.side.highlights.moduels

import CreateHighlightRequest
import UpdateHighlightRequest
import com.side.highlights.models.HighlightDto
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class HighlightService {
    fun get(id: String): HighlightDto {
        TODO("Not yet implemented")
    }

    fun getAll(): List<HighlightDto> {
        TODO("Not yet implemented")
    }

    fun create(file: MultipartFile, body: CreateHighlightRequest): List<HighlightDto> {
        TODO("Not yet implemented")
    }

    fun update(id: String, file: MultipartFile?, update: UpdateHighlightRequest): List<HighlightDto> {
        TODO("Not yet implemented")
    }
}