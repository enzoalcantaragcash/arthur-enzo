package ph.gcash.cadet.lorenzo.alcantara.stonks.utils

import android.content.Context
import android.widget.ImageView
import com.pixplicity.sharp.Sharp
import okhttp3.*
import ph.gcash.cadet.lorenzo.alcantara.stonks.R
import java.io.IOException
import java.io.InputStream

class Utils {

    fun fetchSVG(context: Context, url: String, target: ImageView) {

        var httpClient = OkHttpClient.Builder().cache(Cache(context.cacheDir, 5 * 1024 * 1014) as Cache)
            .build() as OkHttpClient

        var request: Request = Request.Builder().url(url).build()

        httpClient!!.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
               target.setImageResource(R.drawable.pokeball)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                val stream: InputStream = response.body()!!.byteStream()
                Sharp.loadInputStream(stream).into(target)
                stream.close()
            }
        })
    }
}