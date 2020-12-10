async function loadStats() {
    await fetch('http://localhost:8081/api/v1/games/stats');

}

export default {
    LOAD_STATS: loadStats
}