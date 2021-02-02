const puppeteer = require('puppeteer');
const CommSession = require('../platform/CommSession');
const Util = require('../base/utils');
const _static = require('../base/static');
const Api = require('../base/api');

class Session {

    constructor(selector, headless = true) {
        return new Promise(async (resolve, reject) => {
            if (selector == null) {
                reject('empty selector')
            }
            this.browser = await puppeteer.launch({
                args: ['--no-sandbox', '--disable-setuid-sandbox',
                    '--disable-gpu', '--disable-dev-shm-usage',
                    '--no-first-run', '--no-zygote', '--unhandled-rejections=strict'],
                headless: headless,
                slowMo: 20,
            }).catch(reason => reject(reason));
            return resolve(this);
        })
    }

    async close() {
        await this.browser.close()
    }

    async getNewHouse(newHouse) {
        const page = await this.browser.newPage();
        await page.setViewport({
            width: 1400,
            height: 1024
        });
        try {
            await page.goto(newHouse.url +"pg1/", {waitUntil: 'networkidle0'});
            await page.waitFor(_static.twoT);
        }catch (e) {
            await page.goto(newHouse.url + "pg1/", {waitUntil: 'networkidle0'});
            await page.waitFor(_static.twoT);
        }
        let totalHouse = await CommSession.getText(page, newHouse.totalCount);
        let pageCount = totalHouse % 10 === 0 ? totalHouse / 10 : (totalHouse / 10) + 1;
        for (let pageIndex = 1; pageIndex <= pageCount; pageIndex++) {
            try {
                await page.goto(newHouse.url +"pg" + pageIndex + "/", {waitUntil: 'networkidle0'});
                await page.waitFor(_static.twoT);
            }catch (e) {
                await page.goto(newHouse.url +"pg" + pageIndex + "/", {waitUntil: 'networkidle0'});
                await page.waitFor(_static.twoT);
            }
            let items = await page.$$(newHouse.tableItemPrefix);
            console.log(items.length);

            for (let liIdx = 1; liIdx <= items.length; liIdx++) {

                try {
                    let titleSelector = CommSession.childNode(newHouse.tableItemPrefix, liIdx, newHouse.title);
                    let title = await CommSession.getText(page, titleSelector);
                    console.log("=====开始=====");
                    console.log("标题: "+  title);

                    let priceSelector = CommSession.childNode(newHouse.tableItemPrefix, liIdx, newHouse.price);
                    let price = await CommSession.getText(page, priceSelector);
                    console.log("价格： "+ price);

                    let districtSelector = CommSession.childNode(newHouse.tableItemPrefix, liIdx, newHouse.district);
                    let district = await CommSession.getText(page, districtSelector);
                    console.log("区： " + district);

                    let addressSelector = CommSession.childNode(newHouse.tableItemPrefix, liIdx, newHouse.address);
                    let address = await CommSession.getText(page, addressSelector);
                    console.log("地址： " + address);

                    let buildAreaSelector = CommSession.childNode(newHouse.tableItemPrefix, liIdx, newHouse.buildArea);
                    let buildArea = await CommSession.getText(page, buildAreaSelector);
                    console.log("面积：" + buildArea);

                    let firstImageSelector = CommSession.childNode(newHouse.tableItemPrefix, liIdx, newHouse.firstImage);
                    let firstImageUrl = await page.evaluate((selector) => {
                        return document.querySelector(selector).src;
                    }, firstImageSelector);
                    console.log("图片： "+ firstImageUrl);

                    let detailUrlSelector = CommSession.childNode(newHouse.tableItemPrefix, liIdx, newHouse.detailUrl);
                    let detailUrl = await page.evaluate((selector, liIdx) => {
                        window.scroll(0, 350 * liIdx);
                        return document.querySelector(selector).href;
                    }, detailUrlSelector, liIdx);
                    console.log("详情： "+ detailUrl);

                    let tagsSelector = CommSession.childNode(newHouse.tableItemPrefix, liIdx, newHouse.tags);
                    let tags = await page.$$(tagsSelector);
                    let tagList = [];
                    for (let tagIdx = 1; tagIdx <= tags.length; tagIdx++) {
                        let tagSelector = CommSession.childNode(tagsSelector, tagIdx);
                        tagList.push(await CommSession.getText(page, tagSelector));
                    }
                    let houseTags = tagList.join("|");
                    await Api.method.newHouse(title, '贵阳市', district, price, detailUrl, firstImageUrl, houseTags, address, buildArea);

                    console.log("标签： "+ houseTags);
                    console.log("=====结束=====");
                }catch (e) {

                }
            }
        }
    }

    async execute(selector){
        const page = await this.browser.newPage();
        await page.setViewport({
            width: 1400,
            height: 1024
        });

        for (let i = 3; i <= 100; i++) {
            try {
                try {
                    await page.goto(selector.pageList+"pg" + i + "/", {waitUntil: 'networkidle0'});
                    await page.waitFor(_static.twoT);
                }catch (e) {
                    await page.goto(selector.pageList+"pg" + i + "/", {waitUntil: 'networkidle0'});
                    await page.waitFor(_static.twoT);
                }
                let items = await page.$$(selector.pageItem);

                await page.waitFor(items.length);
                if (items.length < 1) {
                    continue;
                }
                for (let liIdx = 1; liIdx <= items.length; liIdx++) {
                    // 标题
                    let title = await CommSession.getText(page, CommSession.childNode(selector.itemOne.prefix, liIdx, selector.itemOne.titleSuffix));
                    // 详情页地址
                    let detailUrl = await page.evaluate((selector) => {
                        if (document.querySelector(selector) != null) {
                            return document.querySelector(selector).href;
                        }
                        return null;
                    }, CommSession.childNode(selector.itemOne.prefix, liIdx, selector.itemOne.titleSuffix));
                    // 地址
                    let address = await CommSession.getText(page, CommSession.childNode(selector.itemOne.prefix, liIdx, selector.itemOne.distinctSuffix));
                    address += await CommSession.getText(page, CommSession.childNode(selector.itemOne.prefix, liIdx, selector.itemOne.addressSuffix));
                    // 图片
                    let firstImage = await page.evaluate((idx) => {
                        window.scroll(0, 500 * idx);
                        let sel = '.sellListContent > li:nth-child('+idx+') > a > img.lj-lazy';
                        let src = '';
                        if (document.querySelector(sel) != null) {
                            src =  document.querySelector(sel).getAttribute("src")
                        }
                        if (src === '' || src == null) {
                            return null;
                        }
                        // 没有加载出来
                        if (src !== 'https://s1.ljcdn.com/feroot/pc/asset/img/blank.gif?_v=20201125232054') {
                            return src;
                        }
                        window.scroll(0, 400 * idx);
                        return document.querySelector(sel).getAttribute("src");
                    }, liIdx);
                    // 规格
                    let norms = await CommSession.getText(page, CommSession.childNode(selector.itemOne.prefix, liIdx, selector.itemOne.normsSuffix));
                    // 价格
                    let price = await CommSession.getText(page, CommSession.childNode(selector.itemOne.prefix, liIdx, selector.itemOne.priceSuffix));
                    if (firstImage == null || firstImage === 'https://s1.ljcdn.com/feroot/pc/asset/img/blank.gif?_v=20201125232054') {
                        continue;
                    }
                    await Api.method.saveHouse(title, detailUrl, address, firstImage, norms, price);
                }
            }catch (e) {

            }
        }
    }
}


module.exports = Session;