let retVal;
let fetch = jest.fn(() => {
    return retVal;
});
fetch.setRetVal = val => {
    retVal = {
        ...Promise.resolve(),
        json: () => val
    };
};

fetch.setRetVal({mock: 'value'});

module.exports = fetch;

