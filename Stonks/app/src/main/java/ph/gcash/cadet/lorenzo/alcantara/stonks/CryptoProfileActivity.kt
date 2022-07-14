package ph.gcash.cadet.lorenzo.alcantara.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import ph.gcash.cadet.lorenzo.alcantara.stonks.api.CoinMarketCapApiClient
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityCryptoProfileBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataInitialResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCryptoProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCryptoProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initMetadata("1")



    }

    //weird kasi json na binabalik ng metadata api
    //kaya kailangan muna mag input ng id
    private fun initMetadata(id: String) {

        //need muna ilagay id
        val call : Call<MetadataDataInitialResponse> = CoinMarketCapApiClient.getCryptoData.getMetadata("1ff15f96-407d-4623-99c2-f067430d757f", id.toInt())

        call.enqueue(object : Callback<MetadataDataInitialResponse> {

            override fun onFailure(call: Call<MetadataDataInitialResponse>, t: Throwable) {
                Log.e("API CALL", t.toString())
                Log.e("API CALL", "ERROR FAILED TO CALL")
            }

            override fun onResponse(
                call: Call<MetadataDataInitialResponse>,
                response: Response<MetadataDataInitialResponse>
            ) {
                if (response.isSuccessful) {
                    var response: MetadataDataInitialResponse = response!!.body()!!
                    var coinMetadata : MetadataDataResponse? = response.data?.get(id)

                    Log.d("API CALL", coinMetadata.toString())

                    binding!!.CryptoCompanyTicker.text = coinMetadata?.symbol
                    binding!!.CryptoCompanyName.text = coinMetadata?.name
                    binding!!.CryptoCompanyDescriptionEdit.text = coinMetadata?.description
                    binding!!.CryptoCompanyMarketCapEdit.text = coinMetadata?.self_reported_market_cap
                    binding!!.CryptoCompanyHomePageEdit.text = coinMetadata?.urls!!.website.toString()

                    val picasso = Picasso.get()
                    picasso
                        .load(coinMetadata.logo)
                        .fit().centerCrop()
                        .into(binding!!.CryptoCompanyIcon)
                }
                else {
                    Log.d("API CALL", "baket")
                }


            }

        })


    }
}