package ph.gcash.cadet.lorenzo.alcantara.stonks.model

class CryptoStockListItem {

    var image = 0
    var ticker = ""
    var companyName = ""

    constructor(image: Int, ticker: String) {
        this.image = image
        this.ticker = ticker
    }

    constructor(image: Int, ticker: String, companyName: String) {
        this.image = image
        this.ticker = ticker
        this.companyName = companyName
    }


}