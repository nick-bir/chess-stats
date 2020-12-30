import mutations from './mutations';

describe('Mutations', () => {
    let store;

    beforeEach(() => {
        store = {};
    });

    describe('setStats', () => {
        it('sets stats data', () => {
            mutations.setStats(store, { a: 'b' });
            expect(store.stats).toMatchObject({ a: 'b' });
        });
    });
    
    describe('toggleFilter', () => {
        it ('adds figures to filters', () => {
            mutations.toggleFilter(store, { filter: 'figure', value: 'pd' });
            expect(store.filters).toMatchObject({ figure: new Set(['pd']) });
            mutations.toggleFilter(store, { filter: 'figure', value: 'kl' });
            expect(store.filters).toMatchObject({ figure: new Set(['pd', 'kl']) });
        });

        it ('removes figures from filters', () => {
            mutations.toggleFilter(store, { filter: 'figure', value: 'pd' });
            mutations.toggleFilter(store, { filter: 'figure', value: 'kl' });
            mutations.toggleFilter(store, { filter: 'figure', value: 'kl' });
            expect(store.filters).toMatchObject({ figure: new Set(['pd']) });
        });
    });

    describe('resetFilter', () => {
        it ('resets figure filter', () => {
            mutations.resetFilter(store, { filter: 'figure' });
            expect(store.filters).toMatchObject({ figure: new Set() });
        });
    });
});
