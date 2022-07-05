package ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap

import com.google.gson.annotations.SerializedName

class CoinMarketCapMapData {

    @SerializedName("data")
    var data : ArrayList<CoinMarketIDMapResponse> = ArrayList()
}