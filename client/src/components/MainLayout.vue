<template>
    <main class="main-layout">
        main
        <ChessBoard/>
    </main>
</template>

<script>
import ChessBoard from './ChessBoard.vue';

export default {
    name: 'MainLayout',
    components: {
        ChessBoard
    }
}
</script>

<style lang="stylus">
    .main-layout
        border: 1px solid silver
</style>