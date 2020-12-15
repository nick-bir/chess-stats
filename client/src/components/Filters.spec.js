import { mount } from '@vue/test-utils';
import Filters from './Filters';

describe('Filters', () => {
    let wrapper;

    beforeEach(() => {
        wrapper = mount(Filters);
    });

    describe('Occupation time', () => {
        it('emits toggleFilter on ChessFigure click', async function() {
            wrapper.find('.ChessFigure').trigger('click');
            await wrapper.vm.$nextTick();
            expect(wrapper.emitted().toggleFilter).toBeTruthy();
            expect(wrapper.emitted().toggleFilter[0][0]).toEqual({filter: 'figure', value: 'r'});
        });

        it('emits resetFilter on "all" click', async function() {
            wrapper.find('.Filters__filter-reset').trigger('click');
            await wrapper.vm.$nextTick();
            expect(wrapper.emitted().resetFilter).toBeTruthy();
            expect(wrapper.emitted().resetFilter[0][0]).toEqual({filter: 'figure'});
        });
    });

    

    // it('emits reset-filter when chess figure clicked', async function() {
    //     lwrapper.find('.ChessFigure').trigger('click');
    //     await wrapper.vm.$nextTick();
    //     expect(wrapper.emitted().toggleFilter).toBeTruthy();
    // });
});
