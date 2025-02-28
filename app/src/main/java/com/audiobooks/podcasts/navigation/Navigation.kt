package com.audiobooks.podcasts.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.audiobooks.podcasts.model.PodcastUiModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
object PodcastList

@Serializable
data class PodcastDetails(
    val podcast: PodcastUiModel
)

object CustomNavType {
    val PodcastType = object : NavType<PodcastUiModel>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): PodcastUiModel {
            return Json.decodeFromString(
                bundle.getString(key)
                    ?: throw IllegalArgumentException("Podcast not found in bundle")
            )
        }

        override fun parseValue(value: String): PodcastUiModel {
            return Json.decodeFromString(value)
        }

        override fun serializeAsValue(value: PodcastUiModel): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: PodcastUiModel) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
