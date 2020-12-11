import mutations from "./mutations";

describe("Mutations", () => {
    describe("SET_STATS", () => {
        let store;

        beforeEach(() => {
            store = {};
        });

        it("sets stats data", () => {
            mutations.SET_STATS(store, { a: "b" });
            expect(store.stats).toMatchObject({ a: "b" });
        });
    });
});
