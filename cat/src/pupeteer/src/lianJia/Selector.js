class Selector {
    constructor() {
        this.pageList =  'https://sh.lianjia.com/ershoufang/';
        this.pageItem = '#content > div.leftContent > ul > li';
        this.itemOne = {
            prefix: '#content > div.leftContent > ul > li',
            titleSuffix: ' > div.info.clear > div.title > a',
            distinctSuffix: '> div.info.clear > div.flood > div > a:nth-child(2)',
            addressSuffix: '> div.info.clear > div.flood > div > a:nth-child(3)',
            imageSuffix: '> a > img.lj-lazy',
            normsSuffix: '> div.info.clear > div.address > div',
            priceSuffix: '> div.info.clear > div.priceInfo > div.unitPrice > span'
        };
        this.newHouse = {
            url: 'https://gy.fang.lianjia.com/loupan/',
            totalCount: 'body > div.resblock-list-container.clearfix > div.resblock-have-find > span.value',
            tableItemPrefix: 'body > div.resblock-list-container.clearfix > ul.resblock-list-wrapper > li',
            price: '> div > div.resblock-price > div > span.number',
            firstImage: '> a > img',
            detailUrl: '> div > div.resblock-name > a',
            title: '> div > div.resblock-name > a',
            district: '> div > div.resblock-location > span:nth-child(1)',
            buildArea: '> div > div.resblock-area > span',
            tags: '> div > div.resblock-tag > span',
            address: '> div > div.resblock-location > a',
        }
    }
}

module.exports = Selector;