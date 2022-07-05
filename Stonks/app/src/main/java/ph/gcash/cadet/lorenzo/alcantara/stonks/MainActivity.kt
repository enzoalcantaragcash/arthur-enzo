package ph.gcash.cadet.lorenzo.alcantara.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ph.gcash.cadet.lorenzo.alcantara.stonks.api.CoinMarketCapApiClient
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityMainBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap.CoinMarketCapMapData
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataInitialResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initListings()
        initMetadata("1")

        binding.button.setOnClickListener{
            Toast.makeText(applicationContext, "Works", Toast.LENGTH_SHORT).show()
        }
    }

    //this returns yung listings by cmc rank
    private fun initListings() {

        //dito sa call function mo ilalagay kung pano ranking and ilan gusto mo idisplay.
        val call : Call<CoinMarketCapMapData> = CoinMarketCapApiClient.getCryptoData.getMap("1ff15f96-407d-4623-99c2-f067430d757f", "cmc_rank", 10)

        call.enqueue(object : Callback<CoinMarketCapMapData> {

            override fun onResponse(
                call: Call<CoinMarketCapMapData>,
                response: Response<CoinMarketCapMapData>
            ) {
                var response: CoinMarketCapMapData = response!!.body()!!
                var coinlist = response.data
                for (coin in coinlist) {
                    Log.d("API CALL", coin.toString())
                }
            }

            override fun onFailure(call: Call<CoinMarketCapMapData>, t: Throwable) {
                Log.e("API CALL", t.toString())
                Log.d("API CALL", " SHET")
            }
        })
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
                }
                else {
                    Log.d("API CALL", "baket")
                }
            }

        })


    }

}