package ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile

import com.google.gson.annotations.SerializedName

class PolygonCompanyAddressResponse {

    @SerializedName("address1")
    var address = ""

    @SerializedName("city")
    var city = ""

    @SerializedName("postal_code")
    var postal_code = ""

    @SerializedName("state")
    var state = ""

    override fun toString(): String {
        return "PolygonCompanyAddressResponse(\naddress='$address', \ncity='$city', \npostal_code='$postal_code', \nstate='$state')"
    }
}