package ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata

import com.google.gson.annotations.SerializedName

class MetadataDataURLResponse {

    @SerializedName("website")
    var website : ArrayList<String> = ArrayList()

    @SerializedName("technical_doc")
    var technical_doc : ArrayList<String> = ArrayList()

    @SerializedName("explorer")
    var explorer : ArrayList<String> = ArrayList()

    @SerializedName("source_code")
    var source_code : ArrayList<String> = ArrayList()

    @SerializedName("message_board")
    var message_board : ArrayList<String> = ArrayList()

    @SerializedName("reddit")
    var reddit : ArrayList<String> = ArrayList()

    @SerializedName("twitter")
    var twitter : ArrayList<String> = ArrayList()

    override fun toString(): String {
        return "MetadataDataURLResponse(website=$website, technical_doc=$technical_doc, explorer=$explorer, source_code=$source_code, message_board=$message_board, reddit=$reddit, twitter=$twitter)"
    }


}