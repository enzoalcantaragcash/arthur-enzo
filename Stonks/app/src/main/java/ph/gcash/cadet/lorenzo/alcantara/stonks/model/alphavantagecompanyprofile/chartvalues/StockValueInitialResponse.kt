package ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.chartvalues

import com.google.gson.annotations.SerializedName

class StockValueInitialResponse {

    @SerializedName("results")
    var results : ArrayList<StockValueResponse>  = ArrayList()

}