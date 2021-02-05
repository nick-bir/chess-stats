function setStats(state, stats) {
    state.stats = stats;
}

function toggleFilter(state, { filter, value }) {
    if (value === '*') {
        return resetFilter(state, { filter, value });
    }

    let filters = state.filters;
    if (filter === 'figure') {
        if (filters.figure.has(value)) filters.figure.delete(value);
        else state.filters.figure.add(value);
        filters.figure = new Set(filters.figure);
    }

    if (filter === 'winner.side') {
        filters.winner.side = value;
    }

    if (filter === 'winner.name') {
        filters.winner.name = value;
    }

    if (filter === 'normalizeData') {
        filters.normalizeData = !filters.normalizeData;
    }
}

function resetFilter(state, { filter }) {
    if (filter === 'figure') {
        state.filters.figure = new Set();
    }

    if (filter === 'winner.side') {
        state.filters.winner.side = null;
    }

    if (filter === 'winner.name') {
        state.filters.winner.name = null;
    }
}

function dataRequestStarted(state) {
    state.dataRequestStarted = true;
}

function dataRequestFinished(state) {
    state.dataRequestStarted = false;
}

function setPlayers(state, players) {
    state.players = players;
}

export default {
    setStats,
    toggleFilter,
    resetFilter,
    dataRequestStarted,
    dataRequestFinished,
    setPlayers
};
