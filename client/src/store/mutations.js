function setStats(state, stats) {
    state.stats = stats;
}
function toggleFilter(state, { filter, val }) {
    if (!state.filters) state.filters = {};
    let filters = state.filters;
    if (filter === 'figure') {
        if (!filters.figure) 
            return (filters.figure = new Set([val]));

        if (filters.figure.has(val))
             return filters.figure.delete(val);

        state.filters.figure.add(val);
    }
}

export default {
    setStats,
    toggleFilter
};
