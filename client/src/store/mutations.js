function setStats(state, stats) {
    state.stats = stats;
}

function toggleFilter(state, { filter, value }) {
    // console.log('--- mutation filter', filter, value);
    if (!state.filters) state.filters = {};
    let filters = state.filters;
    if (filter === 'figure') {
        if (!filters.figure) 
            filters.figure = new Set();

        if (filters.figure.has(value))
            filters.figure.delete(value);
        else
            state.filters.figure.add(value);
    }
}

function resetFilter(state, { filter }) {
    if (!state.filters) state.filters = {};
    let filters = state.filters;
    if (filter === 'figure') {
        filters.figure = new Set();
    }
}

export default {
    setStats,
    toggleFilter,
    resetFilter
};
