<template>
    <main class="main-layout">
        main layout
        <ChessBoard :stats="stats"/>
    </main>
</template>

<script>
import ChessBoard from './ChessBoard.vue';

export default {
    name: 'MainLayout',

    computed: {
        stats() {
            return this.$store?.getters.stats;
        }
    },

    components: {
        ChessBoard
    }
}
</script>

<style lang="stylus">
    .main-layout
        border: 1px solid silver
</style>