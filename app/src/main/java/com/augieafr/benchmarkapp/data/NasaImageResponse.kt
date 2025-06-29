package com.augieafr.benchmarkapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NasaImageResponse(

    @SerialName("collection")
    val collection: Collection? = null
)

@Serializable
data class LinksItem(

    @SerialName("rel")
    val rel: String? = null,

    @SerialName("href")
    val href: String? = null,

    @SerialName("prompt")
    val prompt: String? = null,

    @SerialName("size")
    val size: Int? = null,

    @SerialName("width")
    val width: Int? = null,

    @SerialName("render")
    val render: String? = null,

    @SerialName("height")
    val height: Int? = null
)

@Serializable
data class Metadata(

    @SerialName("total_hits")
    val totalHits: Int? = null
)

@Serializable
data class DataItem(

    @SerialName("keywords")
    val keywords: List<String?>? = null,

    @SerialName("media_type")
    val mediaType: String? = null,

    @SerialName("date_created")
    val dateCreated: String? = null,

    @SerialName("center")
    val center: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("nasa_id")
    val nasaId: String? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("secondary_creator")
    val secondaryCreator: String? = null,

    @SerialName("album")
    val album: List<String?>? = null,

    @SerialName("location")
    val location: String? = null,

    @SerialName("description_508")
    val description508: String? = null,

    @SerialName("photographer")
    val photographer: String? = null
)

@Serializable
data class ItemsItem(

    @SerialName("data")
    val data: List<DataItem?>? = null,

    @SerialName("links")
    val links: List<LinksItem?>? = null,

    @SerialName("href")
    val href: String? = null
)

@Serializable
data class Collection(

    @SerialName("metadata")
    val metadata: Metadata? = null,

    @SerialName("links")
    val links: List<LinksItem?>? = null,

    @SerialName("href")
    val href: String? = null,

    @SerialName("version")
    val version: String? = null,

    @SerialName("items")
    val items: List<ItemsItem?>? = null
)
