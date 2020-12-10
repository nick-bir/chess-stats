import Vue from "vue";
import Vuex from "vuex";
import actions from './actions';

Vue.use(Vuex);

const _store = {
    state: {
        percentageByPiece: {}
    },
    mutations: {
        increment(state) {
            state.count++;
        }
    },
    actions
};

export const store = new Vuex.Store(_store);
