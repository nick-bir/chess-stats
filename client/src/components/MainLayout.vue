<template>
    <main class="MainLayout">
        <ChessBoard :stats="stats" />
        <Filters filters="filters" />
        <LoadingIndicator v-if="dataRequestStarted"/>
    </main>
</template>

<script>
import ChessBoard from './ChessBoard.vue';
import Filters from './Filters.vue';
import LoadingIndicator from './LoadingIndicator.vue';
import { mapGetters } from 'vuex';

export default {
    name: 'MainLayout',

    computed: {
        ...mapGetters(['stats', 'dataRequestStarted'])
    },

    components: {
        ChessBoard,
        Filters,
        LoadingIndicator,
    },
};
</script>

<style lang="stylus">
.MainLayout {
    display: flex
    padding: 10px
    justify-content: center
    // border: 1px solid silver

    .ChessBoard {
        width: 50%
        max-width: 500px
    }

    .Filters {
        margin-left: 40px
    }
}
</style>