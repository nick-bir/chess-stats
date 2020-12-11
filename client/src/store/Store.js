import Vue from 'vue';
import Vuex from 'vuex';
import actions from './actions';
import mutations from './mutations';

Vue.use(Vuex);

const _store = {
    state: {
        stats: {}
    },
    getters: {
        stats(state) {
            return state.stats;
        }
    },
    mutations,
    actions
};

export const store = new Vuex.Store(_store);
