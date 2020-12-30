import { mount, createLocalVue } from '@vue/test-utils';
import Vuex from 'vuex';
import Filters from './Filters';

let localVue = createLocalVue();
localVue.use(Vuex);

describe('Filters', () => {
    let wrapper, store;

    beforeEach(() => {
        store = new Vuex.Store();
        store.dispatch = jest.fn();
   
        wrapper = mount(Filters, { 
            localVue,
            store,
        });
    });

    describe('Occupation time', () => {

        // it('emits resetFilter on "all" click', async function() {
        //     wrapper.find('.Filters__filter-reset').trigger('click');
        //     await wrapper.vm.$nextTick();
        //     expect(wrapper.emitted().resetFilter).toBeTruthy();
        //     expect(wrapper.emitted().resetFilter[0][0]).toEqual({filter: 'figure'});
        // });

        it ('calls dispatch toggleFilter on ChessFigure click', async function() {
            wrapper.find('.ChessFigure').trigger('click');
            expect(store.dispatch).toBeCalledWith('toggleFilter', {filter: 'figure', value: 'r'});
        });

        it ('calls dispatch clearFilter on "all" link click', async function() {
            wrapper.find('.Filters__filter-reset').trigger('click');
            expect(store.dispatch).toBeCalledWith('resetFilter', {filter: 'figure'});
        });
    });

    

    // it('emits reset-filter when chess figure clicked', async function() {
    //     lwrapper.find('.ChessFigure').trigger('click');
    //     await wrapper.vm.$nextTick();
    //     expect(wrapper.emitted().toggleFilter).toBeTruthy();
    // });
});
