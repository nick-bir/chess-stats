import actions from './actions';

jest.mock('node-fetch', () => {
    let retVal;
    let fn = jest.fn(() => retVal);
    fn.setRetVal = val => {
        retVal = {
            ...Promise.resolve(),
            json: () => val
        };
    }
        
    return fn;
});

describe('Actions', () => {
    let store, fetch;
    let { loadStats, toggleFilter, resetFilter } = actions;

    beforeEach(async () => {
        store = {
            dispatch: jest.fn(),
            commit: jest.fn()
        };

        fetch = require('node-fetch');
        fetch.setRetVal({ a: 123 });
    });

    describe('loadStats', () => {
        it('performs request to api server', async function() {
            await loadStats(store);
            expect(fetch).toBeCalledWith(
                'http://localhost:8081/api/v1/games/stats'
            );
        });

        it('saves response from api', async function() {
            await loadStats(store);
            expect(store.commit).toBeCalledWith('setStats', { a: 123 });
        });
    });

    describe('toggleFilter', () => {
        it('commits toggleFilter', () => {
            toggleFilter(store, { filter: 'pl' });
            expect(store.commit).toBeCalledWith('toggleFilter', {
                filter: 'pl'
            });
        });
        it('dispatch requestData on winner filter change', () => {
            toggleFilter(store, { filter: 'winner', value: 'white' });
            expect(store.commit).toBeCalledWith('dataRequestStarted');
            expect(store.dispatch).toBeCalledWith('loadStats');
        });
    });

    // // TODO: move to toggleFilter(filter, *)
    describe('resetFilter', () => {
        it('commits resetFilter', () => {
            resetFilter(store, { filter: 'pl' });
            expect(store.commit).toBeCalledWith('resetFilter', {
                filter: 'pl'
            });
        });
    });
});
