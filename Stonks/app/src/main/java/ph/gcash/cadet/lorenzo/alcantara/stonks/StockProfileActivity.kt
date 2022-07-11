package ph.gcash.cadet.lorenzo.alcantara.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ph.gcash.cadet.lorenzo.alcantara.stonks.api.StocksPolygonIOApiClient
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityStocksBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyAddressResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyBrandingResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyInitialResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyProfileResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.chartvalues.StockValueInitialResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.chartvalues.StockValueResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StockProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStocksBinding
    lateinit var iconView: ImageView
    lateinit var lineChart: LineChart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStocksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCompanyProfile("AAPL")
        initCompanyStockPrices("AAPL")

    }

    private fun initCompanyStockPrices(tickerCompany: String) {
        val call : Call<StockValueInitialResponse> = StocksPolygonIOApiClient.getStocksData.getCompanyStockData(tickerCompany, "1", "day", "2021-07-10",
             "2022-07-10", "asc", "365", "GdDF9nLZ6A7khhWEqUthw_HdpYyokmdC")

        call.enqueue(object : Callback<StockValueInitialResponse> {

            override fun onFailure(call: Call<StockValueInitialResponse>, t: Throwable) {
                Log.e("API CALL", "COMPANY STOCK: "  + t.toString())
                Log.e("API CALL", "ERROR FAILED TO CALL COMPANY STOCK DATA")
            }

            override fun onResponse(
                call: Call<StockValueInitialResponse>,
                response: Response<StockValueInitialResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("API CALL", "Success")
                    lineChart = findViewById(R.id.lineChart)

                    var lineCharDataSet : ArrayList<Entry> = ArrayList()
                    var count = 0.0F
                    for (data in response.body()!!.results) {
                        lineCharDataSet.add(Entry(count, data.closePrice.toFloat()))
                        count++
                    }

                    var lineDataSet = LineDataSet(lineCharDataSet, "data set")
                    var iLineDataSet : ArrayList<ILineDataSet>  = ArrayList()
                    iLineDataSet.add(lineDataSet)

                    var lineData = LineData(iLineDataSet)
                    lineChart.data = lineData
                    lineChart.xAxis.valueFormatter = DateTimeChartFormat()
                    lineChart.invalidate()


                    //var lineDataSet : LineDataSet = LineDataSet()
                }
            }
        })
    }

    private fun initCompanyProfile(tickerCompany : String) {

        val call : Call<PolygonCompanyInitialResponse> = StocksPolygonIOApiClient.getStocksData.getCompanyProfile(tickerCompany, "GdDF9nLZ6A7khhWEqUthw_HdpYyokmdC")

        call.enqueue(object : Callback<PolygonCompanyInitialResponse> {

            override fun onFailure(call: Call<PolygonCompanyInitialResponse>, t: Throwable) {
                Log.e("API CALL", "COMPANY PROFILE: " + t.toString())
                Log.e("API CALL", "ERROR FAILED TO CALL COMPANY PROFILE")
            }

            override fun onResponse(
                call: Call<PolygonCompanyInitialResponse>,
                response: Response<PolygonCompanyInitialResponse>
            ) {
                if(response.isSuccessful) {
                    var response: PolygonCompanyInitialResponse = response!!.body()!!
                    var stock: PolygonCompanyProfileResponse = response.data
                    var address  : PolygonCompanyAddressResponse  = stock.address
                    var branding : PolygonCompanyBrandingResponse = stock.branding

                    Log.d("API CALL" , stock.toString())
                    Log.d("API CALL", address.toString())
                    Log.d("API CALL", branding.toString())

//                    val picasso = Picasso.get()
//                    picasso
//                        .load(branding.icon_url + "?apiKey=GdDF9nLZ6A7khhWEqUthw_HdpYyokmdC")
//                        .error(R.drawable.pokeball)
//                        .fit().centerCrop()
//                        .into(binding!!.companyIcon)

                    iconView = findViewById(R.id.companyIcon)
                    Utils().fetchSVG(applicationContext, branding.logo_url + "?apiKey=GdDF9nLZ6A7khhWEqUthw_HdpYyokmdC", iconView)
                    binding!!.companyName.text = stock.name
                    binding!!.companyTicker.text = stock.ticker
                    binding!!.companyAddress.text = address.address + " " + address.city + " " + address.state + " " + address.postal_code  + " " + stock.phone_number
                    binding!!.companySICEdit.text = stock.sic_description
                    binding!!.companyDescriptionEdit.text = stock.description
                    binding!!.companyHomePageEdit.text = stock.homepage_url
                    binding!!.companyMarketCapEdit.text = stock.market_cap.toString()
                }
            }

        })
    }

    class DateTimeChartFormat : IndexAxisValueFormatter() {

        override fun getFormattedValue(value: Float): String {

            val dateInMillis = value.toLong()
            val date = Calendar.getInstance().apply {
                timeInMillis = dateInMillis
            }.time

            return SimpleDateFormat("MM-dd-yyy", Locale.getDefault()).format(date)
        }
    }
}