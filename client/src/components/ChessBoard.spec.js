import { mount, createLocalVue } from '@vue/test-utils';
import Vuex from 'vuex';
import { _store } from '../store/Store';
import ChessBoard from './ChessBoard';
let localVue = createLocalVue();
localVue.use(Vuex);

describe('ChessBoard', () => {
    let wrapper, store;

    beforeEach(() => {
        let state = {
            stats: {
                percentageByPiece: {
                    a8: {
                        s: 0.5,
                        r: 0.3,
                        q: 0.05
                    },
                    c4: {
                        s: 0.2,
                        r: 0.4,
                        q: 0.06
                    }
                }
            },
            filters: {
                figure: new Set()
            }
        };

        store = new Vuex.Store({
            ..._store,
            state
        });

        wrapper = mount(ChessBoard, {
            localVue,
            store, 
        });
    });

    it('renders stats tiles', async function() {
        expect(wrapper.findAll('.StatsTile').length).toBe(2);
    });
});
