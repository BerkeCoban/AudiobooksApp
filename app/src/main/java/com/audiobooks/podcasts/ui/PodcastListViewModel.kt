package com.audiobooks.podcasts.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.podcasts.model.PodcastUiModel
import com.audiobooks.podcasts.network.PodcastRepository
import com.audiobooks.podcasts.network.ResponseMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PodcastListViewModel : ViewModel() {

    private val _podcastData = MutableStateFlow<List<PodcastUiModel>>(arrayListOf())
    val podcastData: StateFlow<List<PodcastUiModel>> = _podcastData

    private val repository = PodcastRepository()

    fun fetchPodcasts() {
        viewModelScope.launch {
            try {
                val response = repository.getPodcasts()
                if (response.isSuccess) {
                    _podcastData.update {
                        ResponseMapper.map(response.getOrNull())
                    }
                }
            } catch (e: Exception) {
                // we can handle the failed case here if needed. (showing alert dialog etc.)
                Log.d("error: ", e.toString())
            }
        }
    }
}
