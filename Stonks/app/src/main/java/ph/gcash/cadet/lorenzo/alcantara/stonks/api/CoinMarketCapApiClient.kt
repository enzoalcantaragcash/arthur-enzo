package ph.gcash.cadet.lorenzo.alcantara.stonks.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapidmap.CoinMarketCapMapData
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.coinmarketcapmetadata.MetadataDataInitialResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object CoinMarketCapApiClient {

    const val apiKey = "1ff15f96-407d-4623-99c2-f067430d757f"

    const val BASE_URL = "https://pro-api.coinmarketcap.com/"
    val getCryptoData : CryptoAPI
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

            return retrofit.create(CryptoAPI::class.java)

        }

    interface CryptoAPI {

        @GET("v1/cryptocurrency/map")
        fun getMap (
            @Query("CMC_PRO_API_KEY") apiKey: String,
            @Query("sort") sortType :String,
            @Query("limit") limitNum: Int
        ) : Call<CoinMarketCapMapData>

        @GET("v2/cryptocurrency/info")
        fun getMetadata (
            @Query("CMC_PRO_API_KEY") apiKey: String,
            @Query("id") id: String
        ): Call<MetadataDataInitialResponse>
    }
}