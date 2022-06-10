package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import com.google.gson.annotations.SerializedName

data class GetAccountResponse(
    @SerializedName("id") val idStudenta: Int,
    @SerializedName("student") val student: String,
    @SerializedName("acHash") val acHash: String
)
