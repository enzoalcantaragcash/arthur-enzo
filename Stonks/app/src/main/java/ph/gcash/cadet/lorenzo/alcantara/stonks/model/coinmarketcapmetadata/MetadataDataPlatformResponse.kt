package ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata

import com.google.gson.annotations.SerializedName

class MetadataDataPlatformResponse {

        @SerializedName("id")
        var id = 0

        @SerializedName("name")
        var name = ""

        @SerializedName("symbol")
        var symbol = ""

        @SerializedName("slug")
        var slug = ""

        @SerializedName("token_address")
        var token_address = ""

}