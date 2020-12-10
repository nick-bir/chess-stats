import { shallowMount } from "@vue/test-utils";
import App from "./App.vue";

describe("App.vue", () => {
    let wrapper, store;

    beforeEach(() => {
        store = {
            dispatch: jest.fn()
        };

        wrapper = shallowMount(App, {
            propsData: {},
            store
        });
    });

    it("renders MainLayout with chess board", () => {
        expect(wrapper.find(".chess-board").exists());
    });

    it("loads stats from api server when created", () => {
        expect(store.dispatch).toBeCalledWith('LOAD_STATS');
    });
});
