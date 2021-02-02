const util = require('./base/utils');
const Session = require('./zhihu/Session');
const Selector = require('./zhihu/Selector');

async function main() {
    let selector = new Selector();
    const session = await new Session(selector, false);
    // await session.execute(selector);
    await session.execute(selector);
}

main().then(r => util.writeLog(r));






