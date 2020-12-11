import { shallowMount } from '@vue/test-utils'
import MainLayout from '@/components/MainLayout.vue'

describe('App.vue', () => {
  it('renders chess board', () => {
    const wrapper = shallowMount(MainLayout, {
      propsData: {  }
    });
    expect(wrapper.find('.chess-board').exists());
  })
})
