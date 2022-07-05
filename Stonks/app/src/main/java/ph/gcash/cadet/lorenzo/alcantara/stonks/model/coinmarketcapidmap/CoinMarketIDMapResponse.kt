package ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoinMarketIDMapResponse {

    @SerializedName("id")
    var id = -1

    @SerializedName("name")
    var name = ""

    @SerializedName("symbol")
    var symbol = ""

    @SerializedName("slug")
    var slug = ""

    @SerializedName("is_active")
    var is_active = 0

    @SerializedName("status")
    var status = ""

    @SerializedName("first_historical_data")
    var first_historical_data = ""

    @SerializedName("last_historical_data")
    var last_historical_data = ""

    @Expose
    @SerializedName("platform")
    var platform  = CoinMarketIDMapPlatformResponse()

    override fun toString(): String {
        return "CoinMarketIDMapResponse(id=$id, name='$name', symbol='$symbol', slug='$slug', is_active=$is_active, status='$status', first_historical_data='$first_historical_data', last_historical_data='$last_historical_data', platform=$platform)"
    }


}

