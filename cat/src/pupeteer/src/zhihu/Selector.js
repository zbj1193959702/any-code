class Selector {
    constructor() {
        this.pageList =  'https://www.zhihu.com/billboard';
        this.hotList = {
            item: '#root > div > main > div > a',
            // 名次
            ranking: '> div.HotList-itemPre > div.HotList-itemIndex',
            // 标题
            title: '> div.HotList-itemBody > div.HotList-itemTitle',
            // 内容
            content: ' > div.HotList-itemBody > div.HotList-itemExcerpt',
            // 热度
            hot: '> div.HotList-itemBody > div.HotList-itemMetrics',
            // 图片
            image: '> div.HotList-itemImgContainer > img',
        }
    }
}

module.exports = Selector;