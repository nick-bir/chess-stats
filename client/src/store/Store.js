import Vue from 'vue';
import Vuex from 'vuex';
import actions from './actions';
import mutations from './mutations';
import getters from './getters';

Vue.use(Vuex);

const _store = {
    state: () => ({
        stats: {},
        filters: { 
            figure: new Set(),
            winner: null,
        },
        dataRequestStarted: false,
    }),
    getters,
    mutations,
    actions
};

const store = new Vuex.Store(_store);

export {
    store, 
    _store,
}

