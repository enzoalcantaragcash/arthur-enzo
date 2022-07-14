package ph.gcash.cadet.lorenzo.alcantara.stonks.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coingeckochartdata.CoingeckoMarketDataResponse
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap.CoinMarketCapMapData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object CoingeckoApiClient {

    const val BASE_URL = "https://api.coingecko.com/api/v3/"
    val getCryptoChartData : CryptoChartAPI
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

            return retrofit.create(CryptoChartAPI::class.java)

        }

    interface CryptoChartAPI {

        @GET("coins/{id}/market_chart")
        fun getCryptoChartData (
            @Path("id") cryptoID: String,
            @Query("vs_currency") usdCurrency :String,
            @Query("days") daysFromToday: String
        ) : Call<CoingeckoMarketDataResponse>
    }
}