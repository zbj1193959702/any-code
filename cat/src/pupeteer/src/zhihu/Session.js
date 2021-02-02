const puppeteer = require('puppeteer');
const CommSession = require('../platform/CommSession');
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

    async execute(selector){
        const page = await this.browser.newPage();
        await page.setViewport({
            width: 1400,
            height: 1024
        });
        try {
            await page.goto(selector.pageList, {waitUntil: 'networkidle0'});
            await page.waitFor(_static.twoT);
        }catch (e) {
            await page.goto(selector.pageList, {waitUntil: 'networkidle0'});
            await page.waitFor(_static.twoT);
        }
        let hotItem = selector.hotList;
        for (let i = 30; i >= 1; i--) {
            let titleSelector = CommSession.childNode(hotItem.item, i, hotItem.title);
            let title = await CommSession.getText(page, titleSelector);

            let contentSelector = CommSession.childNode(hotItem.item, i, hotItem.content);
            let content = await CommSession.getText(page, contentSelector);

            let hotSelector = CommSession.childNode(hotItem.item, i, hotItem.hot);
            let hot = await CommSession.getText(page, hotSelector);

            let rankingSelector = CommSession.childNode(hotItem.item, i, hotItem.ranking);
            let ranking = await CommSession.getText(page, rankingSelector);

            let imageSelector = CommSession.childNode(hotItem.item, i, hotItem.image);
            let image = await page.evaluate((ele) => {
                    let imageEle = document.querySelector(ele);
                    if (imageEle != null && imageEle.src !== undefined && imageEle.src != null) {
                        return imageEle.src
                    }
                    return null;
                }, imageSelector);

            if (content != null && content.length > 200) {
                content = content.substring(0, 200);
            }
            await Api.method.saveZhiHu(title, content, hot, ranking, image);
        }
        await page.waitFor(_static.twoT);
        await this.close();
    }
}


module.exports = Session;