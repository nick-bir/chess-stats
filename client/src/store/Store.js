
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = {
  state: {
    percentageByPiece: {},
  },
  mutations: {
    increment (state) {
      state.count++;
    }
  }
};

module.export = new Vuex.Store(store);
