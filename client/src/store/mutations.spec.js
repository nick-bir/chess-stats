import mutations from './mutations';
import _store from './Store';

describe('Mutations', () => {
    let state;

    beforeEach(() => {
        state = {
            stats: {},
            filters: {
                figure: new Set()
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
        it('adds figures to filters', () => {
            mutations.toggleFilter(state, { filter: 'figure', value: 'pd' });
            expect(state.filters).toMatchObject({ figure: new Set(['pd']) });
            mutations.toggleFilter(state, { filter: 'figure', value: 'kl' });
            expect(state.filters).toMatchObject({
                figure: new Set(['pd', 'kl'])
            });
        });

        // it ('removes figures from filters', () => {
        //     mutations.toggleFilter(state, { filter: 'figure', value: 'pd' });
        //     mutations.toggleFilter(state, { filter: 'figure', value: 'kl' });
        //     mutations.toggleFilter(state, { filter: 'figure', value: 'kl' });
        //     expect(state.filters).toMatchObject({ figure: new Set(['pd']) });
        // });
    });

    // describe('resetFilter', () => {
    //     it ('resets figure filter', () => {
    //         mutations.resetFilter(store, { filter: 'figure' });
    //         expect(store.filters).toMatchObject({ figure: new Set() });
    //     });
    // });
});
