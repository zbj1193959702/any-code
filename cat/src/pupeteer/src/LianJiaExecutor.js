const util = require('./base/utils');
const Session = require('./lianJia/Session');
const Selector = require('./lianJia/Selector');

async function main() {
    let selector = new Selector();
    const session = await new Session(selector, false);
    // await session.execute(selector);
    await session.getNewHouse(selector.newHouse);
}

main().then(r => util.writeLog(r));






