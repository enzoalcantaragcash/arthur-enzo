package ph.gcash.cadet.lorenzo.alcantara.stonks.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import ph.gcash.cadet.lorenzo.alcantara.stonks.R
import ph.gcash.cadet.lorenzo.alcantara.stonks.StockListingActivity
import ph.gcash.cadet.lorenzo.alcantara.stonks.StockProfileActivity
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.StockListBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.StockListItem


class StockListAdapter(private val context :Context): RecyclerView.Adapter<StockListAdapter.StockViewHolder>() {



    var stockListing = arrayListOf<StockListItem>(
        StockListItem(R.drawable.ic_aapl, "AAPL", "Apple Inc."),
        StockListItem(R.drawable.ic_msft,"MSFT", "Microsoft Corporation"),
        StockListItem(R.drawable.ic_tsm, "TSM", "Taiwan Semiconductor Manufacturing Company Limited"),
        StockListItem(R.drawable.ic_nvidia, "NVDA", "NVIDIA Corporation"),
        StockListItem(R.drawable.ic_avgo, "AVGO", "Broadcom Inc."),
        StockListItem(R.drawable.ic_oracle, "ORCL", "Oracle Corporation"),
        StockListItem(R.drawable.ic_asml, "ASML", "ASML Holding N.V."),
        StockListItem(R.drawable.ic_acn, "ACN", "Accenture plc"),
        StockListItem(R.drawable.ic_adbe, "ADBE", "Adobe Inc."),
        StockListItem(R.drawable.ic_csco, "CSCO", "Cisco Systems, Inc."),
        StockListItem(R.drawable.ic_crm, "CRM", "Salesforce, Inc."),
        StockListItem(R.drawable.ic_intl, "INTC", "Intel Corporation"),
        StockListItem(R.drawable.ic_qcom, "QCOM", "QUALCOMM Incorporated"),
        StockListItem(R.drawable.ic_txn, "TXN", "Texas Instruments Incorporated"),
        StockListItem(R.drawable.ic_ibm, "IBM", "International Business Machines Corporation"),
        StockListItem(R.drawable.ic_amd,"AMD", "Advanced Micro Devices, Inc."),
        StockListItem(R.drawable.ic_intu, "INTU", "Intuit Inc."),
        StockListItem(R.drawable.ic_sap, "SAP", "SAP SE"),
        StockListItem(R.drawable.ic_sony, "SONY", "Sony Group Corporation"),
        StockListItem(R.drawable.ic_now, "NOW", "ServiceNow, Inc."),
        StockListItem(R.drawable.ic_info, "INFY", "InfoSys Limited"),
        StockListItem(R.drawable.ic_amat, "AMAT", "Applied Materials, Inc."),
        StockListItem(R.drawable.ic_adi,"ADI", "Analog Devices, Inc."),
        StockListItem(R.drawable.ic_mu, "MU", "Micron Technology, Inc."),
        StockListItem(R.drawable.ic_fisv, "FISV", "Fiserv Inc")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val stockNameBinding  = StockListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return StockViewHolder(stockNameBinding)
    }

    override fun onBindViewHolder(holder: StockListAdapter.StockViewHolder, position: Int) {

        holder.bindItems(stockListing[position].ticker, stockListing[position].image, stockListing[position].companyName)
        Log.d("BIND ITEM", stockListing[position].ticker)
    }

    override fun getItemCount(): Int {
        return stockListing.size
    }


    inner class StockViewHolder ( var stockNameBinding: StockListBinding)  :RecyclerView.ViewHolder(stockNameBinding.root) {

        fun bindItems(tickerText:String, image:Int, companyText:String) {
            stockNameBinding.listCompanyTicker.text = tickerText
            stockNameBinding.iconCompanyTicker.setImageResource(image)
            stockNameBinding.listCompanyName.text = companyText
            Log.d("BINDED", stockNameBinding.listCompanyTicker.text.toString())

            stockNameBinding.root.setOnClickListener {
                Log.d("Logo","clickable " + tickerText)
                context.startActivity(Intent(context,StockProfileActivity::class.java).putExtra("ticker", tickerText).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        }

//            stockListItems.add(StockListItem(R.drawable.ic_aapl, "AAPL"))
//            stockListItems.add(StockListItem(R.drawable.ic_acn, "ACN"))
//            stockListItems.add(StockListItem(R.drawable.ic_adbe, "ADBE"))

    }
}