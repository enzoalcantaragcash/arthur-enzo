package ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap.CoinMarketIDMapResponse

class PolygonCompanyInitialResponse {

    @SerializedName("results")
    @Expose
    var data = PolygonCompanyProfileResponse()

}