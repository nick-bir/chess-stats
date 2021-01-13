import fetch from 'node-fetch';

async function loadStats({ commit }) {
    const res = await (await fetch('http://localhost:8081/api/v1/games/stats')).json();
    commit('setStats', res);
}

function toggleFilter({ commit, dispatch }, {filter, value}) {
    // console.log('---togglefilter', filter, value)
    commit('toggleFilter', {filter, value});

    if (filter === 'winner') {
        commit('dataRequestStarted');
        dispatch('loadStats');
    }
}

function resetFilter({ commit }, {filter}) {
    commit('resetFilter', {filter});
}

export default {
    loadStats,
    toggleFilter,
    resetFilter
}