package ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap

import com.google.gson.annotations.SerializedName

class CryptoListingResponse {

    @SerializedName("id")
    var id = -1

    @SerializedName("name")
    var name = ""

    @SerializedName("symbol")
    var symbol = ""

    @SerializedName("slug")
    var slug = ""

    @SerializedName("cmc_rank")
    var cmc_rank = -1

    override fun toString(): String {
        return "CryptoListingResponse(id=$id, name='$name', symbol='$symbol', slug='$slug', cmc_rank=$cmc_rank)"
    }


}
