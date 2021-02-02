import mutations from './mutations';

describe('Mutations', () => {
    let state;

    beforeEach(() => {
        state = {
            stats: {},
            filters: {
                figure: new Set(),
                winner: {
                    side: null,
                    name: null
                }
            }
        };
    });

    describe('setStats', () => {
        it('sets stats data', () => {
            mutations.setStats(state, { a: 'b' });
            expect(state.stats).toMatchObject({ a: 'b' });
        });
    });

    describe('toggleFilter', () => {
        describe('figure', () => {
            it('adds figures to filters', () => {
                mutations.toggleFilter(state, {
                    filter: 'figure',
                    value: 'pd'
                });
                expect(state.filters).toMatchObject({
                    figure: new Set(['pd'])
                });
                mutations.toggleFilter(state, {
                    filter: 'figure',
                    value: 'kl'
                });
                expect(state.filters).toMatchObject({
                    figure: new Set(['pd', 'kl'])
                });
            });

            it('removes figures from filters', () => {
                mutations.toggleFilter(state, {
                    filter: 'figure',
                    value: 'pd'
                });
                mutations.toggleFilter(state, {
                    filter: 'figure',
                    value: 'kl'
                });
                mutations.toggleFilter(state, {
                    filter: 'figure',
                    value: 'kl'
                });
                expect(state.filters).toMatchObject({
                    figure: new Set(['pd'])
                });
            });
        });

        describe('winner', () => {
            it('toggles winner.side filter', () => {
                mutations.toggleFilter(state, {
                    filter: 'winner.side',
                    value: 'black'
                });
                expect(state.filters).toMatchObject({
                    winner: { side: 'black' }
                });
            });

            it('resets winner.side filter for *', () => {
                mutations.toggleFilter(state, {
                    filter: 'winner.side',
                    value: '*'
                });
                expect(state.filters).toMatchObject({ winner: { side: null } });
            });
        });

        describe('winner', () => {
            it('toggles normalizeSata filter', () => {
                mutations.toggleFilter(state, {
                    filter: 'normalizeData',
                });
                expect(state.filters).toMatchObject({
                    normalizeData: true
                });
            });
        });
    });

    describe('resetFilter', () => {
        it('resets figure filter', () => {
            mutations.resetFilter(state, { filter: 'figure' });
            expect(state.filters).toMatchObject({ figure: new Set() });
        });
    });
});
