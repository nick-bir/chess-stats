import { shallowMount } from '@vue/test-utils';
import MainLayout from '@/components/MainLayout.vue';
import ChessBoard from '@/components/ChessBoard.vue';
import Filters from '@/components/Filters.vue';

describe('App.vue', () => {
    it('renders chess board and filters', () => {
        const wrapper = shallowMount(MainLayout, {
            propsData: {}
        });
        expect(wrapper.findComponent(ChessBoard).exists()).toBe(true);
        expect(wrapper.findComponent(Filters).exists()).toBe(true);
    });
});
