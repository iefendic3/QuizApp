package ba.etf.rma22.projekat

import com.google.gson.annotations.SerializedName

data class GetOdgovorBodoviResponse(
    @SerializedName("odgovor") val odgovor: Int,
    @SerializedName("pitanje") val pitanje: Int,
    @SerializedName("progres") val progres: Int
)
