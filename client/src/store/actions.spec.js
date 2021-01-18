import actions from './actions';

jest.mock('node-fetch', () => {
    let retVal;
    let fn = jest.fn(() => retVal);
    fn.setRetVal = val => {
        retVal = {
            ...Promise.resolve(),
            json: () => val
        };
    };

    return fn;
});

describe('Actions', () => {
    let store, fetch;
    let { loadStats, toggleFilter, resetFilter } = actions;

    beforeEach(async () => {
        store = {
            dispatch: jest.fn(),
            commit: jest.fn(),
            state: {
                filters: {},
                dataRequestStarted: false
            }
        };

        fetch = require('node-fetch');
        fetch.mockClear();
        fetch.setRetVal({ a: 123 });
    });

    describe('loadStats', () => {
        it('performs request to api server', async () => {
            await loadStats(store);
            expect(fetch).toBeCalledWith(
                'http://localhost:8081/api/v1/games/stats'
            );
        });

        it('saves response from api', async () => {
            await loadStats(store);
            expect(store.commit).toBeCalledWith('setStats', { a: 123 });
        });

        it('applies winner.name filter', async () => {
            store.state.filters = { winner: { name: 'Great,P' } };
            await loadStats(store);
            expect(decodeURIComponent(fetch.mock.calls[0][0])).toBe(
                'http://localhost:8081/api/v1/games/stats?filter=(white="Great,P"+or+black="Great,P")'
            );
        });

        it('applies winner.side filter', async () => {
            store.state.filters = { winner: { side: 'black' } };
            await loadStats(store);
            expect(decodeURIComponent(fetch.mock.calls[0][0])).toBe(
                "http://localhost:8081/api/v1/games/stats?filter=(result=1)"
            );
        });

        it('applies both winner fields filter', async () => {
            store.state.filters = { winner: {side: 'black'} };
            throw new Error('INMPLEMENT HERE');
        });
    });

    describe('toggleFilter', () => {
        it('commits toggleFilter', () => {
            toggleFilter(store, { filter: 'pl' });
            expect(store.commit).toBeCalledWith('toggleFilter', {
                filter: 'pl'
            });
        });
        it('dispatch loadStats on winner.side filter change', () => {
            toggleFilter(store, { filter: 'winner.side', value: 'white' });
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
