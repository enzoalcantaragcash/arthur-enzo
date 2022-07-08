package ph.gcash.cadet.lorenzo.alcantara.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ph.gcash.cadet.lorenzo.alcantara.stonks.api.StocksPolygonIOApiClient
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityStocksBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyAddressResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyBrandingResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyInitialResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StocksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStocksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStocksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCompanyProfile("AAPL")
    }

    private fun initCompanyProfile(tickerCompany : String) {

        val call : Call<PolygonCompanyInitialResponse> = StocksPolygonIOApiClient.getStocksData.getCompanyProfile(tickerCompany, "GdDF9nLZ6A7khhWEqUthw_HdpYyokmdC")

        call.enqueue(object : Callback<PolygonCompanyInitialResponse> {

            override fun onFailure(call: Call<PolygonCompanyInitialResponse>, t: Throwable) {
                Log.e("API CALL", t.toString())
                Log.e("API CALL", "ERROR FAILED TO CALL")
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
                }
            }

        })
    }
}