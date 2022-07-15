package ph.gcash.cadet.lorenzo.alcantara.stonks.model

class CryptoStockListItem {

    var image = 0
    var ticker = ""
    var companyName = ""
    var id = ""
    var slug = ""

    constructor(image: Int, ticker: String) {
        this.image = image
        this.ticker = ticker
    }

    constructor(image: Int, ticker: String, companyName: String) {
        this.image = image
        this.ticker = ticker
        this.companyName = companyName
    }

    constructor(image: Int, ticker: String, companyName: String, id: String, slug: String) {
        this.image = image
        this.ticker = ticker
        this.companyName = companyName
        this.id = id
        this.slug = slug
    }


}