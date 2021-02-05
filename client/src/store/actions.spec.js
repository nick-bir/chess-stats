import actions from './actions';

jest.mock('node-fetch');

describe('Actions', () => {
    let store, fetch;
    let { loadStats, loadPlayers, toggleFilter, resetFilter } = actions;

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

        it('indicates loading progress', async () => {
            await loadStats(store);
            expect(store.commit).toBeCalledWith('dataRequestStarted');
            expect(store.commit).toBeCalledWith('dataRequestFinished');
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
                'http://localhost:8081/api/v1/games/stats?filter=(result=1)'
            );
        });

        // it('applies both winner fields filter', async () => {
        //     store.state.filters = { winner: {side: 'black'} };
        //     throw new Error('INMPLEMENT HERE');
        // });
    });

    describe('loadPlayers', () => {
        it('loads players list', async () => {
            await loadPlayers(store);
            expect(decodeURIComponent(fetch.mock.calls[0][0])).toBe(
                'http://localhost:8081/api/v1/players'
            );
            expect(store.commit).toBeCalledWith('setPlayers', { a: 123 });
        });
    });

    describe('toggleFilter', () => {
        it('commits toggleFilter', () => {
            toggleFilter(store, { filter: 'pl' });
            expect(store.commit).toBeCalledWith('toggleFilter', {
                filter: 'pl'
            });
        });
        it('dispatch loadStats on winner.side filter change', async () => {
            await toggleFilter(store, {
                filter: 'winner.side',
                value: 'white'
            });
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
