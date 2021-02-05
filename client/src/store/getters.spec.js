import getters from './getters';

describe('Getters', () => {
    let state;

    beforeEach(() => {
        state = {
            stats: {
                percentageByPiece: {
                    a8: {
                        s: 0.5,
                        r: 0.3,
                        q: 0.05
                    },
                    c4: {
                        s: 0.2,
                        r: 0.4,
                        q: 0.06
                    }
                }
            },
            filters: {
                figure: new Set()
            },
            players: [['a', 123], ['b', 456]]
        };
    });

    describe('filteredStats', () => {
        it('applies default "s" figure filter', () => {
            let filteredStats = getters.filteredStats(state);
            expect(filteredStats).toEqual({ a8: 0.5, c4: 0.2 });
        });

        it('applies single figure filter', () => {
            state.filters.figure.add('r');
            let filteredStats = getters.filteredStats(state);
            expect(filteredStats).toEqual({ a8: 0.3, c4: 0.4 });
        });

        it('applies multiple figures filter', () => {
            state.filters.figure.add('r');
            state.filters.figure.add('q');
            let filteredStats = getters.filteredStats(state);
            expect(filteredStats).toEqual({ a8: 0.35, c4: 0.46 });
        });

        it('applies data normalization', () => {
            state.filters.figure.add('s');
            state.filters.normalizeData = true;
            let filteredStats = getters.filteredStats(state);
            expect(filteredStats).toEqual({ a8: 100, c4: 40 });
        });

        it('no errors on empty stats', () => {
            state.stats = {};
            let filteredStats = getters.filteredStats(state);
            expect(filteredStats).toEqual({});
        });
    });

    describe('players', () => {
        it('returns 1d players array', () => {
            let players = getters.players(state);
            expect(players).toEqual(['a', 'b']);
        });
    });
});
