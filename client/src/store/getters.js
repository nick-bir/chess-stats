export default {
    stats(state) {
        return state.stats;
    },

    filteredStats(state) {
        let figuresFilter = new Set(['s']);
        if (state.filters.figure.size) 
            figuresFilter = state.filters.figure;

        let stats = {};
        let pbpStats = state.stats.percentageByPiece || {};
        for (let [cell, figures] of Object.entries(pbpStats)) {
            stats[cell] = 0;
            for (let [figure, val] of Object.entries(figures)) {
                stats[cell] += figuresFilter.has(figure) ? val : 0;
            }
        }

        if (state.filters.normalizeData) {
            const maxStat = Math.max(...Object.values(stats));
            for (let figure of Object.keys(stats)) {
                stats[figure] = stats[figure] / maxStat * 100;
            }
        }

        return stats;
    },

    filters(state) {
        return state.filters;
    },

    dataRequestStarted(state) {
        return state.dataRequestStarted
    }

};
