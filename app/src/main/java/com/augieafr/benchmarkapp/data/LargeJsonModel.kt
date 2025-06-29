package com.augieafr.benchmarkapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LargeJsonModel(

    @SerialName("actor")
    val actor: Actor? = null,

    @SerialName("public")
    val jsonMemberPublic: Boolean? = null,

    @SerialName("payload")
    val payload: Payload? = null,

    @SerialName("repo")
    val repo: Repo? = null,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("type")
    val type: String? = null
)

@Serializable
data class Payload(

    @SerialName("ref")
    val ref: String? = null,

    @SerialName("pusher_type")
    val pusherType: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("ref_type")
    val refType: String? = null,

    @SerialName("master_branch")
    val masterBranch: String? = null
)

@Serializable
data class Actor(

    @SerialName("avatar_url")
    val avatarUrl: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("login")
    val login: String? = null,

    @SerialName("gravatar_id")
    val gravatarId: String? = null,

    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Repo(

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("url")
    val url: String? = null
)
