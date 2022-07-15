package ph.gcash.cadet.lorenzo.alcantara.stonks.model.coingeckochartdata

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class CoingeckoMarketDataResponse {

    @SerializedName("prices")
    var prices  : MutableMap<Long?, BigDecimal?>? = null
}