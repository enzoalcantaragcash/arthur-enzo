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
import ph.gcash.cadet.lorenzo.alcantara.stonks.api.CoingeckoApiClient
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityStocksTestChartBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coingeckochartdata.CoingeckoMarketDataResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap.CoinMarketCapMapData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoTestChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStocksTestChartBinding
    private lateinit var lineChart:LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStocksTestChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMarketData()
    }

    fun initMarketData () {

        val call : Call<CoingeckoMarketDataResponse> = CoingeckoApiClient.getCryptoChartData.getCryptoChartData("bitcoin", "usd", "365")

        call.enqueue(object: Callback<CoingeckoMarketDataResponse> {

            override fun onResponse(
                call: Call<CoingeckoMarketDataResponse>,
                response: Response<CoingeckoMarketDataResponse>
            ) {
                var response: CoingeckoMarketDataResponse = response!!.body()!!
                var pricelist = response.prices
                Log.d("API CALL", pricelist!!.keys.toString())
                lineChart = findViewById(R.id.lineChartCrypto)
                var lineCharDataSet : ArrayList<Entry> = ArrayList()

                for (entry in pricelist.entries.iterator()) {
                    lineCharDataSet.add(Entry(entry.key!!.toFloat(), entry.value!!.toFloat()))
                }

                var lineDataSet = LineDataSet(lineCharDataSet, "data set")
                lineDataSet.apply {
                    mode = LineDataSet.Mode.CUBIC_BEZIER
                    lineWidth = 2F
                    isHighlightEnabled=true
                    setDrawValues(false)
                    setDrawHighlightIndicators(false)
                    setDrawCircles(false)
                    color = ContextCompat.getColor(applicationContext, R.color.moss_green)
                }
                var iLineDataSet : ArrayList<ILineDataSet>  = ArrayList()
                iLineDataSet.add(lineDataSet)

                var lineData = LineData(iLineDataSet)
                lineChart.data = lineData
                lineChart.xAxis.valueFormatter = StockProfileActivity.DateTimeChartFormat()
                lineChart.xAxis.setCenterAxisLabels(true)
                lineChart.axisRight.isEnabled = false
                lineChart.xAxis.granularity = 4F
                lineChart.legend.isEnabled = false
                lineChart.description.isEnabled= false
                lineChart.xAxis.setLabelCount(5)
                lineChart.xAxis.textSize = 6F

                lineChart.invalidate()
            }

            override fun onFailure(call: Call<CoingeckoMarketDataResponse>, t: Throwable) {
                Log.e("API CALL", t.toString())
            }


        })

    }
}