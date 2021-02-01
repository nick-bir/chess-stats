import { mount, createLocalVue } from '@vue/test-utils';
import vueBem from './vue-bem.vue';

let localVue = createLocalVue();
localVue.mixin(vueBem);

describe('VueBem', () => {
    let wrapper, component;

    beforeEach(async () => {
        component = {
            name: 'Comp',
            template:
                '<div :class="blockClasses"><div :class="elemClasses"></div></div>',
            computed: {
                blockClasses() {
                    return this.$bem({
                        mods: {
                            visible: true,
                            flat: false,
                            size: 'xl'
                        }
                    });
                },
                elemClasses() {
                    return this.$bem({
                        block: 'myblock',
                        elem: 'someelem',
                        mods: {
                            visible: true,
                            flat: false,
                            size: 'xl'
                        }
                    });
                },
                noModsClasses() {
                    return this.$bem({
                        block: 'myblock',
                        elem: 'myelem',
                        visible: true,
                        flat: false,
                        size: 'xl'
                    });
                }
            }
        };

        wrapper = mount(component, {
            localVue
        });
    });

    describe('$bem()', () => {
        let blockClasses, elemClasses, noModsClasses;

        beforeEach(() => {
            blockClasses = wrapper.vm.blockClasses;
            elemClasses = wrapper.vm.elemClasses;
            noModsClasses = wrapper.vm.noModsClasses;
        });

        it('vm has $bem() method', () => {
            expect(wrapper.vm.$bem instanceof Function).toBe(true);
        });

        it('appends "block" and "elem" if they are presented in bemOptions', () => {
            expect(elemClasses.myblock__someelem).toBe(true);
        });

        describe('block', () => {
            it('returns object', () => {
                expect(typeof blockClasses).toBe('object');
            });

            it('returns block`s name class', () => {
                expect(blockClasses).toMatchObject({ Comp: true });
            });

            it('returns true boolean mods', () => {
                expect(blockClasses).toMatchObject({ Comp_flat: false });
            });

            it('skip false boolean mods', () => {
                expect(blockClasses).toMatchObject({ Comp_visible: true });
            });

            it('returns enumerable mods', () => {
                expect(blockClasses).toMatchObject({ Comp_size_xl: true });
            });
        });

        describe('elem', () => {
            it('returns object', () => {
                expect(typeof elemClasses).toBe('object');
            });

            it('returns elems`s name class', () => {
                expect(elemClasses).toMatchObject({ myblock__someelem: true });
            });

            it('returns boolean mods', () => {
                expect(elemClasses).toMatchObject({
                    myblock__someelem_visible: true
                });
            });

            it('returns enumerable mods', () => {
                expect(elemClasses).toMatchObject({
                    myblock__someelem_size_xl: true
                });
            });
        });

        describe('assuming mods if no bemOptions.mods field provided', () => {
            it('takes bemOptions.field as _field_ mod', () => {
                expect(noModsClasses).toMatchObject({
                    myblock__myelem_visible: true
                });
            });

            it('skips "block" and "elem" fields in bemOptions', () => {
                expect(elemClasses.myblock__myelem_block).toBe(undefined);
                expect(elemClasses.myblock__myelem_elem).toBe(undefined);
            });
        });
    });

    describe('$bemElem', () => {
        let bemOptions;
        beforeEach(() => {
            bemOptions = {
                block: 'myblock',
                flat: true
            };
        });

        it('applies elemName', () => {
            expect(
                vueBem.methods.$bemElem('myelem', bemOptions)
            ).toMatchObject({ myblock__myelem: true });
        });

        it('applies mods from bemOptions', () => {
            expect(
                vueBem.methods.$bemElem('myelem', bemOptions)
            ).toMatchObject({ myblock__myelem_flat: true });
        });
    });
});
