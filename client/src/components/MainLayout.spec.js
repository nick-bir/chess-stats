import { shallowMount } from '@vue/test-utils'
import MainLayout from '@/components/MainLayout.vue'

describe('App.vue', () => {
  it('renders chess board and filters', () => {
    const wrapper = shallowMount(MainLayout, {
      propsData: {  }
    });
    expect(wrapper.find('.ChessBoard').exists());
    expect(wrapper.find('.Filters').exists());
  })
})
