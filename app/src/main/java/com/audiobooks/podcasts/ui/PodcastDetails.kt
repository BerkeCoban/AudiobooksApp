package com.audiobooks.podcasts.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.audiobooks.podcasts.R
import com.audiobooks.podcasts.model.PodcastUiModel
import com.audiobooks.podcasts.ui.components.ImageWithRoundedCorners
import com.audiobooks.podcasts.ui.components.PodcastDetailToolBar

@Composable
fun PodcastDetailsScreen(item: PodcastUiModel, onBackPressed: () -> Unit) {

    val favoriteState = rememberSaveable { mutableStateOf(false) }

    Column {
        PodcastDetailToolBar {
            onBackPressed.invoke()
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(25.dp)
        ) {
            item {
                Text(
                    text = item.title, fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = item.publisher, fontSize = 18.sp,
                    color = Color.Gray
                )
                ImageWithRoundedCorners(item.image, 230.dp, 15.dp)
                Button(
                    modifier = Modifier
                        .width(110.dp)
                        .padding(top = 15.dp),
                    onClick = {
                        favoriteState.value = !favoriteState.value
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.button_color)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = if (favoriteState.value) stringResource(R.string.podcast_detail_favourited)
                        else stringResource(R.string.podcast_detail_favourite)
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = item.description, fontSize = 12.sp,
                    color = Color.Gray
                )
            }

        }
    }

}