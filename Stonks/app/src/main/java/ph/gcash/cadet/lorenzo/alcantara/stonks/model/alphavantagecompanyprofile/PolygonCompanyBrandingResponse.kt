package ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile

import com.google.gson.annotations.SerializedName

class PolygonCompanyBrandingResponse {

    @SerializedName("icon_url")
    var icon_url = ""

    @SerializedName("logo_url")
    var logo_url = ""

    override fun toString(): String {
        return "PolygonCompanyBrandingResponse(\nicon_url='$icon_url', \nlogo_url='$logo_url')"
    }
}