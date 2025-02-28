package com.audiobooks.podcasts.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.audiobooks.podcasts.R


// Lazy column structure for PodcastListScreen.
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumnUI(
    items: ArrayList<@Composable () -> Unit>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp)
            .background(Color.White),
    ) {
        stickyHeader {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    stringResource(R.string.podcast_list_title),
                    modifier = Modifier.padding(15.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }
        }
        items(items) { chartItem ->
            chartItem()
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Composable
fun PodcastDetailToolBar(onBackPressed: () -> Unit) {
    Row(horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 20.dp)
            .clickable {
                onBackPressed.invoke()
            }) {
        Image(
            modifier = Modifier
                .size(18.dp),
            imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
            contentDescription = stringResource(R.string.podcast_detail_back)
        )
        Text(
            text = stringResource(R.string.podcast_detail_back),
            fontSize = 15.sp,
            modifier = Modifier
                .padding(start = 5.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ImageWithRoundedCorners(url: String, size: Dp, paddingTop: Dp = 0.dp) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "Square Image",
        modifier = Modifier
            .size(size)
            .padding(top = paddingTop)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}