package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Istrazivanje (
    @SerializedName("id") val id: Int,
    @SerializedName("naziv") val naziv: String,
    @SerializedName("godina") val godina: Int
)