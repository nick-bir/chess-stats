function setStats(state, stats) {
    state.stats = stats;
}

function toggleFilter(state, { filter, value }) {
    let filters = state.filters;
    if (filter === 'figure') {
        if (filters.figure.has(value))
            filters.figure.delete(value);
        else
            state.filters.figure.add(value);
        filters.figure = new Set(filters.figure);
    } else {
        filters[filter] = value === '*' ? null : value;
    }
}

function resetFilter(state, { filter }) {
    if (filter === 'figure') {
        state.filters.figure = new Set();
    }
}

function dataRequestStarted(state) {
    state.dataRequestStarted = true;
}

export default {
    setStats,
    toggleFilter,
    resetFilter,
    dataRequestStarted
};
