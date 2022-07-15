package ph.gcash.cadet.lorenzo.alcantara.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import ph.gcash.cadet.lorenzo.alcantara.stonks.adapter.CryptoListAdapter
import ph.gcash.cadet.lorenzo.alcantara.stonks.api.CoinMarketCapApiClient
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityCryptoListBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap.CoinMarketCapMapData
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataInitialResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCryptoListBinding
    private lateinit var recyclerViewAdapter : CryptoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCryptoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListings()

        recyclerViewAdapter = CryptoListAdapter(applicationContext)
        binding.cryptoRecyclerView.adapter = recyclerViewAdapter
        binding.cryptoRecyclerView.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)


    }

    //this returns yung listings by cmc rank
    private fun initListings() {

        //dito sa call function mo ilalagay kung pano ranking and ilan gusto mo idisplay.
        val call : Call<CoinMarketCapMapData> = CoinMarketCapApiClient.getCryptoData.getMap("1ff15f96-407d-4623-99c2-f067430d757f", "cmc_rank", 25)

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
                response.data
            }

            override fun onFailure(call: Call<CoinMarketCapMapData>, t: Throwable) {
                Log.e("API CALL", t.toString())
                Log.d("API CALL", " SHET")
            }
        })
    }

}