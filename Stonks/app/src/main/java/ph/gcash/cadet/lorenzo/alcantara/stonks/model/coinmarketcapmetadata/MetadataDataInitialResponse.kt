package ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MetadataDataInitialResponse {

    @SerializedName("data")
    @Expose
    var data: MutableMap<String?, MetadataDataResponse?>? = null
}