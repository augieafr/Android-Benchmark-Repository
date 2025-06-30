package com.augieafr.kmpbenchmarkapp.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicSubmissions(

    @SerialName("architecture-interior")
    val architectureInterior: ArchitectureInterior? = null,

    @SerialName("film")
    val film: Film? = null,

    @SerialName("archival")
    val archival: Archival? = null,

    @SerialName("street-photography")
    val streetPhotography: StreetPhotography? = null
)

@Serializable
data class Film(

    @SerialName("approved_on")
    val approvedOn: String? = null,

    @SerialName("status")
    val status: String? = null
)

@Serializable
data class Sponsorship(

    @SerialName("sponsor")
    val sponsor: Sponsor? = null,

    @SerialName("tagline_url")
    val taglineUrl: String? = null,

    @SerialName("tagline")
    val tagline: String? = null,
)

@Serializable
data class Links(

    @SerialName("portfolio")
    val portfolio: String? = null,

    @SerialName("self")
    val self: String? = null,

    @SerialName("html")
    val html: String? = null,

    @SerialName("photos")
    val photos: String? = null,

    @SerialName("likes")
    val likes: String? = null,

    @SerialName("download")
    val download: String? = null,

    @SerialName("download_location")
    val downloadLocation: String? = null
)

@Serializable
data class Sponsor(

    @SerialName("total_photos")
    val totalPhotos: Int? = null,

    @SerialName("accepted_tos")
    val acceptedTos: Boolean? = null,

    @SerialName("bio")
    val bio: String? = null,

    @SerialName("total_likes")
    val totalLikes: Int? = null,

    @SerialName("portfolio_url")
    val portfolioUrl: String? = null,

    @SerialName("profile_image")
    val profileImage: ProfileImage? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null,

    @SerialName("total_promoted_illustrations")
    val totalPromotedIllustrations: Int? = null,

    @SerialName("for_hire")
    val forHire: Boolean? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("total_promoted_photos")
    val totalPromotedPhotos: Int? = null,

    @SerialName("links")
    val links: Links? = null,

    @SerialName("total_collections")
    val totalCollections: Int? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("total_illustrations")
    val totalIllustrations: Int? = null,

    @SerialName("first_name")
    val firstName: String? = null,

    @SerialName("instagram_username")
    val instagramUsername: String? = null,

    @SerialName("username")
    val username: String? = null
)

@Serializable
data class ArchitectureInterior(

    @SerialName("approved_on")
    val approvedOn: String? = null,

    @SerialName("status")
    val status: String? = null
)

@Serializable
data class Urls(

    @SerialName("small")
    val small: String? = null,

    @SerialName("small_s3")
    val smallS3: String? = null,

    @SerialName("thumb")
    val thumb: String? = null,

    @SerialName("raw")
    val raw: String? = null,

    @SerialName("regular")
    val regular: String? = null,

    @SerialName("full")
    val full: String? = null
)

@Serializable
data class AlternativeSlugs(

    @SerialName("de")
    val de: String? = null,

    @SerialName("ko")
    val ko: String? = null,

    @SerialName("pt")
    val pt: String? = null,

    @SerialName("ja")
    val ja: String? = null,

    @SerialName("en")
    val en: String? = null,

    @SerialName("it")
    val it: String? = null,

    @SerialName("fr")
    val fr: String? = null,

    @SerialName("es")
    val es: String? = null
)


@Serializable
data class ProfileImage(

    @SerialName("small")
    val small: String? = null,

    @SerialName("large")
    val large: String? = null,

    @SerialName("medium")
    val medium: String? = null
)

@Serializable
data class Archival(

    @SerialName("approved_on")
    val approvedOn: String? = null,

    @SerialName("status")
    val status: String? = null
)

@Serializable
data class User(

    @SerialName("total_photos")
    val totalPhotos: Int? = null,

    @SerialName("accepted_tos")
    val acceptedTos: Boolean? = null,

    @SerialName("bio")
    val bio: String? = null,

    @SerialName("total_likes")
    val totalLikes: Int? = null,

    @SerialName("portfolio_url")
    val portfolioUrl: String? = null,

    @SerialName("profile_image")
    val profileImage: ProfileImage? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null,

    @SerialName("total_promoted_illustrations")
    val totalPromotedIllustrations: Int? = null,

    @SerialName("for_hire")
    val forHire: Boolean? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("total_promoted_photos")
    val totalPromotedPhotos: Int? = null,

    @SerialName("links")
    val links: Links? = null,

    @SerialName("total_collections")
    val totalCollections: Int? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("total_illustrations")
    val totalIllustrations: Int? = null,

    @SerialName("first_name")
    val firstName: String? = null,

    @SerialName("instagram_username")
    val instagramUsername: String? = null,

    @SerialName("username")
    val username: String? = null
)

@Serializable
data class UnsplashResponse(

    @SerialName("topic_submissions")
    val topicSubmissions: TopicSubmissions? = null,

    @SerialName("color")
    val color: String? = null,

    @SerialName("sponsorship")
    val sponsorship: Sponsorship? = null,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("liked_by_user")
    val likedByUser: Boolean? = null,

    @SerialName("urls")
    val urls: Urls? = null,

    @SerialName("alternative_slugs")
    val alternativeSlugs: AlternativeSlugs? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null,

    @SerialName("width")
    val width: Int? = null,

    @SerialName("blur_hash")
    val blurHash: String? = null,

    @SerialName("asset_type")
    val assetType: String? = null,

    @SerialName("links")
    val links: Links? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("user")
    val user: User? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("height")
    val height: Int? = null,

    @SerialName("likes")
    val likes: Int? = null
)

@Serializable
data class StreetPhotography(

    @SerialName("status")
    val status: String? = null
)
