import actions from "./actions";

describe("Actions", () => {
    let store;
    let { loadStats, toggleFilter, resetFilter } = actions;

    beforeEach(() => {
        store = {
            dispatch: jest.fn(),
            commit: jest.fn(),
        }
    })

    describe('loadStats', () => {
        beforeEach(() => {
            global.fetch = jest.fn(()=>({
                ...Promise.resolve(),
                json() {
                    return {a: 123};
                }
            }));
            
        });

        afterEach(() => fetch.mockClear());
    
        it("performs request to api server", () => {
            loadStats(store);
            expect(fetch).toBeCalledWith('http://localhost:8081/api/v1/games/stats');
        });
    
        it("saves response from api", async function() {
            await loadStats(store);
            expect(store.commit).toBeCalledWith('setStats', {a: 123});
        });
    });
    
    describe('toggleFilter', () => {
        it("commits toggleFilter", () => {
            toggleFilter(store, { filter: 'pl' });
            expect(store.commit).toBeCalledWith('toggleFilter', {filter: 'pl'});
        });
    });
    
    describe('resetFilter', () => {
        it("commits resetFilter", () => {
            resetFilter(store, { filter: 'pl' });
            expect(store.commit).toBeCalledWith('resetFilter', {filter: 'pl'});
        });
    });
});
