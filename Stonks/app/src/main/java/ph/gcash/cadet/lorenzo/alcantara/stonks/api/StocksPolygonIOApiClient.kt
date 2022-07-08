package ph.gcash.cadet.lorenzo.alcantara.stonks.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyInitialResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.alphavantagecompanyprofile.PolygonCompanyProfileResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object StocksPolygonIOApiClient {

    const val BASE_URL = "https://api.polygon.io/"
    val getStocksData : AlphaVantageAPI
        get() {
            val gson = GsonBuilder().setLenient().create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(AlphaVantageAPI::class.java)

        }

    interface AlphaVantageAPI {

        @GET("v3/reference/tickers/{companyTicker}")
        fun getCompanyProfile(
            @Path("companyTicker") companyTicker : String,
            @Query("apiKey") apiKey : String,
            ) : Call<PolygonCompanyInitialResponse>

    }
}