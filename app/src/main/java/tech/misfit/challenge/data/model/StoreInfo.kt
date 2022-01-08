package tech.misfit.challenge.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Makinul on Jan 03, 2022.
 */

data class StoreInfo(
    @SerializedName("name") val name: String,
    @SerializedName("rating") val rating: Float = 0.0f,
    @SerializedName("openingTime") val openingTime: String? = null,
    @SerializedName("closingTime") val closingTime: String? = null
)