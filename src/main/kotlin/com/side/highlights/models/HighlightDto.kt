package com.side.highlights.models

import java.net.URI
import java.net.URL

data class HighlightDto (
    val id: Int,
    val link: URL?,
//    val text: String?,
    val title: String?,
    val author: String?,
    val page: Int?,
)