import { Store } from "vuex";

async function loadStats({ commit }) {
    let res = await (await fetch('http://localhost:8081/api/v1/games/stats')).json();
    commit('setStats', res);
}

function toggleFilter({ commit }, {filter}) {
    commit('toggleFilter', {filter});
}

export default {
    loadStats,
    toggleFilter
}