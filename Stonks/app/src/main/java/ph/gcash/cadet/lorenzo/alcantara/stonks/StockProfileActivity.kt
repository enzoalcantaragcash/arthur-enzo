package ph.gcash.cadet.lorenzo.alcantara.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
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
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class StockProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStocksBinding
    lateinit var iconView: ImageView
    lateinit var lineChart: LineChart


    override fun onCreate(savedInstanceState: Bundle?) {

        var extras = intent.extras
        var ticker = extras!!.getString("ticker")
        super.onCreate(savedInstanceState)
        binding = ActivityStocksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ticker != null) {
            initCompanyProfile(ticker)
        }
        var endDate = DateTime.now()
        Log.d("TEST JODA", endDate.toString("yyyy-MM-dd"))
        var startDate = endDate.minusDays(250)
        var dateFormat = "yyyy-MM-dd"
        Log.d("TEST JODA", startDate.toString("yyyy-MM-dd"))

        if (ticker != null) {
            initCompanyStockPrices(ticker, startDate.toString(dateFormat),endDate.toString(dateFormat))
        }

    }

    private fun initCompanyStockPrices(tickerCompany: String, startDate: String, endDate:String) {
        val call : Call<StockValueInitialResponse> = StocksPolygonIOApiClient.getStocksData.getCompanyStockData(tickerCompany, "1", "day", startDate,
             endDate, "desc", "5000", getString(R.string.apiKey_alphavantage))

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

                    var dateInMilis = DateTime.parse(endDate, DateTimeFormat.forPattern("yyyy-MM-dd")).millis
                    var lineCharDataSet : ArrayList<Entry> = ArrayList()
                    Log.d("API DEBUG", response.body()!!.results.size.toString())

                    for (data in response.body()!!.results) {
                        lineCharDataSet.add(Entry(data.time.toFloat(), data.closePrice.toFloat()))
                    }
                    binding!!.stockCurrentPriceEdit.text = lineCharDataSet[0].y.toString()
                    lineCharDataSet.reverse()
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
                    lineChart.xAxis.valueFormatter = Utils.DateTimeChartFormat()
                    lineChart.xAxis.setCenterAxisLabels(true)
                    lineChart.axisRight.isEnabled = false
                    lineChart.xAxis.granularity = 4F
                    lineChart.legend.isEnabled = false
                    lineChart.description.isEnabled= false
                    lineChart.xAxis.setLabelCount(5)
                    lineChart.xAxis.textSize = 6F

                    lineChart.invalidate()

                }
            }
        })
    }

    private fun initCompanyProfile(tickerCompany : String) {

        val call : Call<PolygonCompanyInitialResponse> = StocksPolygonIOApiClient.getStocksData.getCompanyProfile(tickerCompany, getString(R.string.apiKey_alphavantage))

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
                    Log.d("API CALL LOGO", branding.icon_url+"?apiKey=GdDF9nLZ6A7khhWEqUthw_HdpYyokmdC")

                    iconView = findViewById(R.id.companyIcon)
                    Utils().fetchSVG(applicationContext, branding.logo_url + "?apiKey=GdDF9nLZ6A7khhWEqUthw_HdpYyokmdC", iconView)
                    binding!!.companyName.text = stock.name
                    binding!!.companyTicker.text = stock.ticker
                    binding!!.companyAddress.text = address.address + " " + address.city + " " + address.state + " " + address.postal_code  + "\n" + stock.phone_number
                    binding!!.companySICEdit.text = stock.sic_description
                    binding!!.companyDescriptionEdit.text = stock.description
                    binding!!.companyHomePageEdit.text = stock.homepage_url
                    binding!!.companyMarketCapEdit.text = stock.market_cap.toString()
                    binding!!.companyTotalEmployEdit.text = stock.total_employees.toString()
                }
            }

        })
    }


}