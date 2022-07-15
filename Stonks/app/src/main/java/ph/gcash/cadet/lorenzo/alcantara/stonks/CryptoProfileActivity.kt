package ph.gcash.cadet.lorenzo.alcantara.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.squareup.picasso.Picasso
import ph.gcash.cadet.lorenzo.alcantara.stonks.api.CoinMarketCapApiClient
import ph.gcash.cadet.lorenzo.alcantara.stonks.api.CoingeckoApiClient
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityCryptoProfileBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coingeckochartdata.CoingeckoMarketDataResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataInitialResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCryptoProfileBinding
    lateinit var lineChart : LineChart

    override fun onCreate(savedInstanceState: Bundle?) {

        var extras = intent.extras
        var id = extras!!.getString("id")
        var slug = extras!!.getString("slug")
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (id != null) {
            initMetadata(id)
        }

        if (slug != null) {
            initMarketData(slug)
        }


    }

    //weird kasi json na binabalik ng metadata api
    //kaya kailangan muna mag input ng id
    private fun initMetadata(id: String) {

        //need muna ilagay id
        val call : Call<MetadataDataInitialResponse> = CoinMarketCapApiClient.getCryptoData.getMetadata("1ff15f96-407d-4623-99c2-f067430d757f", id)

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

    fun initMarketData (slug:String) {

        val call : Call<CoingeckoMarketDataResponse> = CoingeckoApiClient.getCryptoChartData.getCryptoChartData(slug, "usd", "365")

        call.enqueue(object: Callback<CoingeckoMarketDataResponse> {

            override fun onResponse(
                call: Call<CoingeckoMarketDataResponse>,
                response: Response<CoingeckoMarketDataResponse>
            ) {
                if(response.isSuccessful){
                    var response: CoingeckoMarketDataResponse = response!!.body()!!
                    var pricelist = response.prices
                    Log.d("API CALL", pricelist!!.keys.toString())
                    lineChart = findViewById(R.id.CryptoLineChart)
                    var lineCharDataSet: ArrayList<Entry> = ArrayList()

                    for (entry in pricelist.entries.iterator()) {
                        lineCharDataSet.add(Entry(entry.key!!.toFloat(), entry.value!!.toFloat()))
                    }

                    binding!!.CryptoCurrentPriceEdit.text = lineCharDataSet[lineCharDataSet.size -1].y.toString()

                    var lineDataSet = LineDataSet(lineCharDataSet, "data set")
                    lineDataSet.apply {
                        mode = LineDataSet.Mode.CUBIC_BEZIER
                        lineWidth = 2F
                        isHighlightEnabled = true
                        setDrawValues(false)
                        setDrawHighlightIndicators(false)
                        setDrawCircles(false)
                        color = ContextCompat.getColor(applicationContext, R.color.moss_green)
                    }
                    var iLineDataSet: ArrayList<ILineDataSet> = ArrayList()
                    iLineDataSet.add(lineDataSet)

                    var lineData = LineData(iLineDataSet)
                    lineChart.data = lineData
                    lineChart.xAxis.valueFormatter = StockProfileActivity.DateTimeChartFormat()
                    lineChart.xAxis.setCenterAxisLabels(true)
                    lineChart.axisRight.isEnabled = false
                    lineChart.xAxis.granularity = 4F
                    lineChart.legend.isEnabled = false
                    lineChart.description.isEnabled = false
                    lineChart.xAxis.setLabelCount(5)
                    lineChart.xAxis.textSize = 6F

                    lineChart.invalidate()

                }
            }

            override fun onFailure(call: Call<CoingeckoMarketDataResponse>, t: Throwable) {
                Log.e("API CALL", t.toString())
            }


        })

    }
}