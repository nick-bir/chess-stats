import { mount, createLocalVue } from '@vue/test-utils';
import Vuex from 'vuex';
import { _store } from '../store/Store';
import Filters from './Filters';

let localVue = createLocalVue();
localVue.use(Vuex);

describe('Filters', () => {
    let wrapper, store, dispatchSpy;

    beforeEach(() => {
        store = new Vuex.Store(_store);
        dispatchSpy = jest.spyOn(store, 'dispatch');

        wrapper = mount(Filters, {
            localVue,
            store
        });
    });

    afterEach(() => dispatchSpy.mockRestore());

    describe('Occupation time', () => {
        it('sets figure filter', async function() {
            wrapper.find('.ChessFigure').trigger('click');
            expect(store.state.filters).toMatchObject({
                figure: new Set(['r'])
            });
        });

        it('clears figure filter when "all" clicked', async function() {
            wrapper.find('.Filters__filter-reset').trigger('click');
            expect(store.state.filters).toMatchObject({ figure: new Set() });
        });

        it('marks figure as selected', async function() {
            const figure = wrapper.find('.ChessFigure');
            expect(figure.classes()).not.toContain(
                'Filters__filter-option_selected'
            );
            figure.trigger('click');
            await wrapper.vm.$nextTick();
            expect(figure.classes()).toContain(
                'Filters__filter-option_selected'
            );
        });
    });

    describe('Occupation time', () => {
        it('sets winner filter', async function() {
            wrapper.find('.Filters__filter_winner button').trigger('click');
            expect(store.state.filters).toMatchObject({ winner: {side: 'black'} });
        });

        it('marks winner type as selected', async function() {
            const winner = wrapper.find('.Filters__filter_winner button');
            expect(winner.classes()).not.toContain(
                'Filters__filter-option_selected'
            );
            winner.trigger('click');
            await wrapper.vm.$nextTick();
            expect(winner.classes()).toContain(
                'Filters__filter-option_selected'
            );
        });
    });
});
