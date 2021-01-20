import { shallowMount } from "@vue/test-utils";
import App from "./App.vue";
import MainLayout from "./components/MainLayout.vue";

jest.mock('node-fetch');

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
        expect(wrapper.findComponent(MainLayout).exists()).toBe(true);
    });

    it("loads stats from api server when created", () => {
        expect(store.dispatch).toBeCalledWith('loadStats');
    });
});
