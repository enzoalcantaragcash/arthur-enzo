package ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class PolygonCompanyProfileResponse {

    @SerializedName("ticker")
    var ticker = ""

    @SerializedName("name")
    var name = ""

    @SerializedName("sic_description")
    var sic_description = ""

    @SerializedName("description")
    var description = ""

    @SerializedName("homepage_url")
    var homepage_url = ""

    @SerializedName("address")
    @Expose
    var address  = PolygonCompanyAddressResponse()

    @SerializedName("branding")
    @Expose
    var branding = PolygonCompanyBrandingResponse()

    @SerializedName("total_employees")
    var total_employees  = 0.toBigDecimal()

    @SerializedName("phone_number")
    var phone_number = ""

    @SerializedName("market_cap")
    var market_cap = 0.toBigDecimal()

    @SerializedName("list_date")
    var list_date = ""

    override fun toString(): String {
        return "PolygonCompanyProfileResponse(\nticker='$ticker', \nname='$name', \nsic_description='$sic_description', \ndescription='$description', \nhomepage_url='$homepage_url', \naddress=$address, \nbranding=$branding, \ntotal_employees=$total_employees, \nphone_number='$phone_number', \nmarket_cap=$market_cap, \nlist_date='$list_date')"
    }


}
