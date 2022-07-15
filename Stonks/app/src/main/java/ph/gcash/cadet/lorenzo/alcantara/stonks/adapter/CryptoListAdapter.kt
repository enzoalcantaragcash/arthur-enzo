package ph.gcash.cadet.lorenzo.alcantara.stonks.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.gcash.cadet.lorenzo.alcantara.stonks.CryptoProfileActivity
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

        holder.bindItems(cryptoStockListItem[position].ticker,cryptoStockListItem[position].image,
            cryptoStockListItem[position].companyName, cryptoStockListItem[position].id, cryptoStockListItem[position].slug)
        Log.d("BIND ITEM", cryptoStockListItem[position].ticker)
    }

    override fun getItemCount(): Int {

        return cryptoStockListItem.size
    }

    inner class CryptoViewHolder ( var cryptoNameBinding: CryptoListBinding)  :RecyclerView.ViewHolder(cryptoNameBinding.root) {

        fun bindItems(tickerText:String, image:Int,  companyText:String, id:String , slug:String){
            cryptoNameBinding.listCryptoCompanyTicker.text = tickerText
            cryptoNameBinding.listCryptoCompanyName.text = companyText
            cryptoNameBinding.iconCryptoCompanyTicker.setImageResource(image)

            cryptoNameBinding.root.setOnClickListener {
                Log.d("CARD", "Clickable")
                context.startActivity(Intent(context, CryptoProfileActivity::class.java).putExtra("id", id).putExtra("slug", slug).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        }


    }

    var cryptoStockListItem = arrayListOf<CryptoStockListItem>(
        CryptoStockListItem(R.drawable.ic_btc  , "BTC", "Bitcoin", "1", "bitcoin"),
        CryptoStockListItem(R.drawable.ic_eth,"ETH", "Ethereum", "1027", "ethereum"),
        CryptoStockListItem(R.drawable.ic_usdt, "USDT", "Tether", "825", "tether"),
        CryptoStockListItem(R.drawable.ic_usdc, "USDC", "USD Coin", "3408", "usd-coin"),
        CryptoStockListItem(R.drawable.ic_bnb, "BNB", "BNB", "1839", "binancecoin"),

        CryptoStockListItem(R.drawable.ic_busd, "BUSD", "Binance USD", "4687", "binance-usd"),
        CryptoStockListItem(R.drawable.ic_xrp, "XRP", "XRP", "52", "xrp"),
        CryptoStockListItem(R.drawable.ic_ada, "ADA", "Cardano", "2010", "cardano"),
        CryptoStockListItem(R.drawable.ic_sol, "SOL", "Solana", "5426", "solana"),
        CryptoStockListItem(R.drawable.ic_doge, "DOGE", "Dogecoin", "74", "dogecoin"),

        CryptoStockListItem(R.drawable.ic_dai, "DAI", "DAI", "4943", "dai"),
        CryptoStockListItem(R.drawable.ic_dot, "DOT", "Polkadot", "6636", "polkadot"),
        CryptoStockListItem(R.drawable.ic_trx, "TRX", "Tron", "1958", "tron"),
        CryptoStockListItem(R.drawable.ic_shib, "SHIB", "Shiba Inu", "5994", "shiba-inu"),
        CryptoStockListItem(R.drawable.ic_matic, "MATIC", "Polygon", "3890", "polygon"),

        CryptoStockListItem(R.drawable.ic_avax, "AVAX", "Avalanche", "5805", "avalance"),
        CryptoStockListItem(R.drawable.ic_leo, "LEO", "UNUS SED LEO", "3957", "leo"),
        CryptoStockListItem(R.drawable.ic_uni, "UNI", "Uniswap", "7083", "uniswap"),
        CryptoStockListItem(R.drawable.ic_wbtc, "WBTC", "Wrapped Bitcoin", "3717", "wrapped-bitcoin"),
        CryptoStockListItem(R.drawable.ic_ltc, "LTC", "Litecoin", "2", "litecoin"),

        CryptoStockListItem(R.drawable.ic_ftt, "FTT", "FTX Token", "4195", "ftx-token"),
        CryptoStockListItem(R.drawable.ic_cro, "CRO", "Cronos", "3635", "cronos"),
        CryptoStockListItem(R.drawable.ic_link, "LINK", "Chainlink", "1975", "chainlink"),
        CryptoStockListItem(R.drawable.ic_xlm, "XLM", "Stellar", "512", "stellar"),
        CryptoStockListItem(R.drawable.ic_near, "NEAR", "Near Protocol", "6535", "near")
        )

}