package com.nvropotov.network.impl.data

import com.google.gson.annotations.SerializedName

data class YandexResponse(
    @SerializedName("method") var method: String? = String(),
    @SerializedName("href") var href: String? = String(),
    @SerializedName("templated") var templated: Boolean? = false,
)