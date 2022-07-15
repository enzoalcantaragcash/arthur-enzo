package ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.chartvalues

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

class StockValueResponse {

    @SerializedName("c")
    var closePrice : Double = 0.0

    @SerializedName("h")
    var highestPrice : Double= 0.0

    @SerializedName("l")
    var lowestPrice : Double= 0.0

    @SerializedName("t")
    var time : BigInteger = BigInteger.valueOf(0)

    override fun toString(): String {
        return "StockValueResponse(closePrice=$closePrice, highestPrice=$highestPrice, lowestPrice=$lowestPrice)"
    }


}