package com.example.coingechkoproject.network.data

import com.google.gson.annotations.SerializedName


class IcoLinks(
    @SerializedName("web")
    val web: String? = null,
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("github")
    val github: String? = null,
    @SerializedName("twitter")
    val twitter: String? = null,
    @SerializedName("facebook")
    val facebook: String? = null,
    @SerializedName("telegram")
    val telegram: String? = null,
    @SerializedName("whitepaper")
    val whitepaper: String? = null
)