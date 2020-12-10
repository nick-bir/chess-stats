import actions from "./actions";

describe("Actions", () => {
    
    

    describe('LOAD_STATS', () => {
        let loadStats = actions.LOAD_STATS;

        beforeEach(() => {
            global.fetch = jest.fn();
        });

        afterEach(() => fetch.mockClear());
    
        it("performs request to api server", () => {
            loadStats();
            expect(fetch).toBeCalledWith('http://localhost:8081/api/v1/games/stats');
        });
    
        it("returns JSON response from api", () => {
            loadStats();
        });
    });
});
