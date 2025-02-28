package com.audiobooks.podcasts.model

import kotlinx.serialization.Serializable

@Serializable
data class PodcastUiModel(
    val title: String,
    val description: String,
    val image: String,
    val publisher: String
)