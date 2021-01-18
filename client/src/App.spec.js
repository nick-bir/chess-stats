import { shallowMount } from "@vue/test-utils";
import App from "./App.vue";
import MainLayout from "./components/MainLayout.vue";

describe("App.vue", () => {
    let wrapper, store;

    beforeEach(() => {
        store = {
            dispatch: jest.fn(),
        };

        wrapper = shallowMount(App, {
            propsData: {},
            store
        });
    });

    it("renders MainLayout", () => {
        console.log('--- html', wrapper.html())
        expect(wrapper.findComponent(MainLayout).exists()).toBe(true);
    });

    it("loads stats from api server when created", () => {
        expect(store.dispatch).toBeCalledWith('loadStats');
    });

    // it("displays 'loading-indicator' when data requested", () => {
    //     expect(store.dispatch).toBeCalledWith('loadStats');
    //     expect(wrapper.find(".loading-indictor").exists()).toBe(true);
    // });
});
