package com.audiobooks.podcasts.network

import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.model.PodcastUiModel


object ResponseMapper {

    // maps network response model to ui response model.
    fun map(data: List<Podcast>?): List<PodcastUiModel> {
        return arrayListOf<PodcastUiModel>().apply {
            data?.forEach { item ->
                add(
                    PodcastUiModel(
                        title = item.title,
                        description = item.description,
                        image = item.image,
                        publisher = item.publisher
                    )
                )
            }
        }.toList()
    }
}
