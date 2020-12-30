async function loadStats({ commit }) {
    let res = await (await fetch('http://localhost:8081/api/v1/games/stats')).json();
    commit('setStats', res);
}

function toggleFilter({ commit }, {filter, value}) {
    // console.log('---togglefilter', filter, value)
    commit('toggleFilter', {filter, value});
}

function resetFilter({ commit }, {filter}) {
    commit('resetFilter', {filter});
}

export default {
    loadStats,
    toggleFilter,
    resetFilter
}