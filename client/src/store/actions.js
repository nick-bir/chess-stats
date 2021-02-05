import fetch from 'node-fetch';

async function loadStats({ commit, state }) {
    let url = new URL('http://localhost:8081/api/v1/games/stats');
    let params = {};
    let winner = state.filters.winner || {};
    params.filter = '';
    if (winner.name)
        params.filter += `white="${winner.name}" or black="${winner.name}"`;
    if (winner.side) {
        params.filter +=
            (params.filter.length ? ' and ' : '') +
            `result=${winner.side === 'white' ? 0 : 1}`;
    }

    if (params.filter.length) {
        params.filter = `(${params.filter})`;
    } else {
        delete params.filter;
    }

    url.search = new URLSearchParams(params).toString();

    // console.log('----fetching:', url.toString());

    commit('dataRequestStarted');
    const res = await (await fetch(url.toString())).json();
    commit('dataRequestFinished');
    commit('setStats', res);
}

async function loadPlayers({ commit }) {
    let url = new URL('http://localhost:8081/api/v1/players');
    const res = await (await fetch(url.toString())).json();
    commit('setPlayers', res);
}

function toggleFilter({ commit, dispatch }, { filter, value }) {
    commit('toggleFilter', { filter, value });

    if (filter === 'winner.side') {
        dispatch('loadStats');
    }
}

function resetFilter({ commit }, { filter }) {
    commit('resetFilter', { filter });
}

export default {
    loadStats,
    loadPlayers,
    toggleFilter,
    resetFilter
};
