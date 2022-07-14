package ph.gcash.cadet.lorenzo.alcantara.stonks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding!!.cryptoButton.setOnClickListener() {
//            startActivity(Intent(this, StockListingActivity::class.java))
//        }

        binding!!.stocksButton.setOnClickListener() {
            startActivity(Intent(this, StockListingActivity::class.java))
        }

    }
}