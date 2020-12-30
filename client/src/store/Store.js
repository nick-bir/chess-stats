import Vue from 'vue';
import Vuex from 'vuex';
import actions from './actions';
import mutations from './mutations';
import getters from './getters';

Vue.use(Vuex);

const _store = {
    state: {
        stats: {}
    },
    getters,
    mutations,
    actions
};

export const store = new Vuex.Store(_store);
