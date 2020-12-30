<template>
    <main class="MainLayout">
        <ChessBoard :stats="stats"/>
        <Filters filters="filters" />
    </main>
</template>

<script>
import ChessBoard from './ChessBoard.vue';
import Filters from './Filters.vue';

export default {
    name: 'MainLayout',

    computed: {
        stats() {
            return this.$store?.getters.stats;
        }
    },

    components: {
        ChessBoard,
        Filters
    }
}
</script>

<style lang="stylus">
    .MainLayout
        display: flex
        padding: 10px
        justify-content: center
        border: 1px solid silver

        .ChessBoard
            width: 50%
            max-width: 500px

        .Filters
            margin-left: 20px
        
     
</style>