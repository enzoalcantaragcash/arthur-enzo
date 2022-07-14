package ph.gcash.cadet.lorenzo.alcantara.stonks.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.gcash.cadet.lorenzo.alcantara.stonks.R
import ph.gcash.cadet.lorenzo.alcantara.stonks.StockProfileActivity
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.CryptoListBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.databinding.StockListBinding
import ph.gcash.cadet.lorenzo.alcantara.stonks.model.CryptoStockListItem

class CryptoListAdapter(private val context : Context): RecyclerView.Adapter<CryptoListAdapter.CryptoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val cryptoNameBinding  = CryptoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CryptoViewHolder(cryptoNameBinding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {

        holder.bindItems(cryptoStockListItem[position].ticker,cryptoStockListItem[position].image,  cryptoStockListItem[position].companyName)
        Log.d("BIND ITEM", cryptoStockListItem[position].ticker)
    }

    override fun getItemCount(): Int {

        return cryptoStockListItem.size
    }

    inner class CryptoViewHolder ( var cryptoNameBinding: CryptoListBinding)  :RecyclerView.ViewHolder(cryptoNameBinding.root) {

        fun bindItems(tickerText:String, image:Int,  companyText:String){
            cryptoNameBinding.listCryptoCompanyTicker.text = tickerText
            cryptoNameBinding.listCryptoCompanyName.text = companyText
            cryptoNameBinding.iconCryptoCompanyTicker.setImageResource(image)
        }


    }

    var cryptoStockListItem = arrayListOf<CryptoStockListItem>(
        CryptoStockListItem(R.drawable.ic_btc  , "BTC", "Bitcoin"),
        CryptoStockListItem(R.drawable.ic_eth,"ETH", "Ethereum"),
        CryptoStockListItem(R.drawable.ic_usdt, "USDT", "Tether"),
        CryptoStockListItem(R.drawable.ic_usdc, "USDC", "USD Coin"),
        CryptoStockListItem(R.drawable.ic_bnb, "BNB", "BNB"),

        CryptoStockListItem(R.drawable.ic_busd, "BUSD", "Binance USD"),
        CryptoStockListItem(R.drawable.ic_xrp, "XRP", "XRP"),
        CryptoStockListItem(R.drawable.ic_ada, "ADA", "Cardano"),
        CryptoStockListItem(R.drawable.ic_sol, "SOL", "Solana"),
        CryptoStockListItem(R.drawable.ic_doge, "DOGE", "Dogecoin"),

        CryptoStockListItem(R.drawable.ic_dai, "DAI", "DAI"),
        CryptoStockListItem(R.drawable.ic_dot, "DOT", "Polkadot"),
        CryptoStockListItem(R.drawable.ic_trx, "TRX", "Tron"),
        CryptoStockListItem(R.drawable.ic_shib, "SHIB", "Shiba Inu"),
        CryptoStockListItem(R.drawable.ic_matic, "MATIC", "Polygon"),

        CryptoStockListItem(R.drawable.ic_avax, "AVAX", "Avalanche"),
        CryptoStockListItem(R.drawable.ic_leo, "LEO", "UNUS SED LEO"),
        CryptoStockListItem(R.drawable.ic_uni, "UNI", "Uniswap"),
        CryptoStockListItem(R.drawable.ic_wbtc, "WBTC", "Wrapped Bitcoin"),
        CryptoStockListItem(R.drawable.ic_ltc, "LTC", "Litecoin"),

        CryptoStockListItem(R.drawable.ic_ftt, "FTT", "FTX Token"),
        CryptoStockListItem(R.drawable.ic_cro, "CRO", "Cronos"),
        CryptoStockListItem(R.drawable.ic_link, "LINK", "Chainlink"),
        CryptoStockListItem(R.drawable.ic_xlm, "XLM", "Stellar"),
        CryptoStockListItem(R.drawable.ic_near, "NEAR", "Near Protocol")
        )

}