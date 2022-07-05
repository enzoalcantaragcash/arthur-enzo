package ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap.CoinMarketIDMapPlatformResponse

class MetadataDataResponse (id: String) {


    @SerializedName("id")
    var id = -1

    @SerializedName("name")
    var name = ""

    @SerializedName("symbol")
    var symbol = ""

    @SerializedName("category")
    var category = ""

    @SerializedName("slug")
    var slug = ""

    @SerializedName("logo")
    var logo = ""

    @SerializedName("description")
    var description = ""

    @SerializedName("date_added")
    var date_added = ""

    @SerializedName("date_launched")
    var date_launched = ""

    @SerializedName("self_reported_circulating_supply")
    var self_reported_circulating_supply = ""

    @SerializedName("self_reported_market_cap")
    var self_reported_market_cap = ""

    @Expose
    @SerializedName("platform")
    var platform  = MetadataDataPlatformResponse()

    @Expose
    @SerializedName("urls")
    var urls = MetadataDataURLResponse()

    override fun toString(): String {
        return "MetadataDataResponse(\nid=$id, \nname='$name', \nsymbol='$symbol', \ncategory='$category', \nslug='$slug', \nlogo='$logo', \ndescription='$description', \ndate_added='$date_added', \ndate_launched='$date_launched', \nself_reported_circulating_supply='$self_reported_circulating_supply', \nself_reported_market_cap='$self_reported_market_cap', platform=$platform, urls= " + urls.toString()
    }



}