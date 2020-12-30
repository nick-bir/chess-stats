import { mount, createLocalVue } from '@vue/test-utils';
import Vuex from 'vuex';
import Filters from './Filters';
import _store from '../store/Store';

let localVue = createLocalVue();
localVue.use(Vuex);

describe('Filters', () => {
    let wrapper, store, dispatch;

    beforeEach(() => {
        // store = new Vuex.Store();
        // store.dispatch = jest.fn();
        // store.getters = {
        //     filters: () => new Set()
        // }

        store = new Vuex.Store(require('../store/Store')._store)

        // store.dispatch = jest.fn();
        dispatch = jest.spyOn(store, 'dispatch');
   
        wrapper = mount(Filters, { 
            localVue,
            store,
        });
    });

    afterEach(() => dispatch.mockRestore());

    describe('Occupation time', () => {

        it ('sets figure filter', async function() {
            wrapper.find('.ChessFigure').trigger('click');
            expect(store.state.filters).toMatchObject({ figure: new Set(['r']) });
        });
            
        it ('clears figure filter when "all" clicked', async function() {
            wrapper.find('.Filters__filter-reset').trigger('click');
            expect(store.state.filters).toMatchObject({ figure: new Set() });
        });

        // it ('marks figure as selected', async function() {
        //     const figure = wrapper.find('.ChessFigure')
        //     expect(figure.classes()).not.toContain('Filters__filter-option_selected');
        //     figure.trigger('click');
        //     expect(figure.classes()).toContain('Filters__filter-option_selected');
        // });
    });
});
