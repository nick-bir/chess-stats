import actions from "./actions";

describe("Actions", () => {
    describe('LOAD_STATS', () => {
        let store;
        let loadStats = actions.LOAD_STATS;

        beforeEach(() => {
            global.fetch = jest.fn(()=>({
                ...Promise.resolve(),
                json() {
                    return {a: 123};
                }

            }));
            store = {
                dispatch: jest.fn(),
                commit: jest.fn(),
            }
        });

        afterEach(() => fetch.mockClear());
    
        it("performs request to api server", () => {
            loadStats(store);
            expect(fetch).toBeCalledWith('http://localhost:8081/api/v1/games/stats');
        });
    
        it("saves response from api", async function() {
            await loadStats(store);
            expect(store.commit).toBeCalledWith('SET_STATS', {a: 123});
        });
    });
});
