package com.example.dipractice

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchImageResponse(
    @SerializedName("meta") val meta : MetaResponse?,
    @SerializedName("documents") val documents : List<ImageDocumentResponse>?,
)

data class MetaResponse(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("pageable_count") val pageableCount: Int?,
    @SerializedName("is_end") val isEnd: Boolean?,
)

data class ImageDocumentResponse(
    @SerializedName("collection") val collection: Any?,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("display_sitename") val displaySitename: String?,
    @SerializedName("doc_url") val docUrl: String?,
    @SerializedName("datetime") val datetime: Date?,
)

data class SearchVideoResponse(
    @SerializedName("meta") val meta : MetaResponse?,
    @SerializedName("documents") val documents : List<VideoDocumentResponse>?,
)

data class VideoDocumentResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url : String?,
    @SerializedName("datetime") val datetime : Date?,
    @SerializedName("play_time") val playTime : Int?,
    @SerializedName("thumbnail") val thumnail : String,
    @SerializedName("author") val author : String,
)
