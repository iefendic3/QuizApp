package ba.etf.rma22.projekat

import com.google.gson.annotations.SerializedName

data class GetMessageResponse (
    @SerializedName("message") val message: String

)