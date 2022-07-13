package ph.gcash.cadet.lorenzo.alcantara.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ph.gcash.cadet.lorenzo.alcantara.stonks.adapter.StockListAdapter

import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityStockListingBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.ActivityStocksBinding

class StockListingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStockListingBinding
    private lateinit var recyclerViewAdapter : StockListAdapter

    private var layoutStockManager: RecyclerView.LayoutManager? = null
    private var stockListAdapter : RecyclerView.Adapter<StockListAdapter.StockViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewAdapter = StockListAdapter(applicationContext)
        binding.recyclerView.adapter =recyclerViewAdapter
        //binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)



    }
}