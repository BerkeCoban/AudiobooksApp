package com.audiobooks.podcasts.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.audiobooks.podcasts.model.PodcastUiModel
import com.audiobooks.podcasts.ui.components.ImageWithRoundedCorners
import com.audiobooks.podcasts.ui.components.LazyColumnUI

@Composable
fun PodcastListScreen(onShowDetails: (podcast: PodcastUiModel) -> Unit) {
    val viewModel: PodcastListViewModel = viewModel()
    val podcastListState = viewModel.podcastData.collectAsState()
    // start network call.
    viewModel.fetchPodcasts()

    PodcastListUI(
        podcastListState.value,
        onShowDetails = onShowDetails
    )
}

@Composable
private fun PodcastListUI(
    podcastData: List<PodcastUiModel>,
    onShowDetails: (podcast: PodcastUiModel) -> Unit
) {
    if (podcastData.isNotEmpty()) {
        LazyColumnUI(items = arrayListOf<@Composable () -> Unit>().apply {
            for (item in podcastData) {
                add {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                onShowDetails(item)
                            }
                    ) {
                        ImageWithRoundedCorners(item.image, 80.dp)
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = item.title, fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = item.publisher, fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        })
    } else {
        // show empty view.
    }
}



