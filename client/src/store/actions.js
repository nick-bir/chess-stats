async function loadStats({ commit }) {
    let res = await (await fetch('http://localhost:8081/api/v1/games/stats')).json();
    commit('SET_STATS', res);
}

export default {
    LOAD_STATS: loadStats
}