package tech.misfit.challenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Makinul on Jan 03, 2022.
 */

@Parcelize
data class ProductInfo(
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int = 0,
    @SerializedName("imageUrl") val imageUrl: String? = null
) : Parcelable