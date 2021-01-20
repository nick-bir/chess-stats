import { shallowMount, createLocalVue } from '@vue/test-utils';
import Vuex from 'vuex';
import MainLayout from '@/components/MainLayout.vue';
import ChessBoard from '@/components/ChessBoard.vue';
import Filters from '@/components/Filters.vue';
import _store from '@/store/Store.js';

let localVue = createLocalVue();
localVue.use(Vuex);

describe('MainLayout.vue', () => {
    let wrapper, store;
    beforeEach(() => {
        store = new Vuex.Store(_store);
        wrapper = shallowMount(MainLayout, {
            localVue,
            store
        });
        jest.spyOn(store, 'dispatch');
    });

    it('renders chess board and filters', () => {
        expect(wrapper.findComponent(ChessBoard).exists()).toBe(true);
        expect(wrapper.findComponent(Filters).exists()).toBe(true);
    });

    
    // it("displays 'loading-indicator' when data requested", () => {
    //     store.dispatch('loadStats');
    //     expect(store.dispatch).toBeCalledWith('loadStats');
    //     expect(wrapper.find(".loading-indictor").exists()).toBe(true);
    // });
});
