<template>
    <div id="app">
        <MainLayout />
    </div>
</template>

<script>
import {store} from "./store/Store.js";
import MainLayout from "./components/MainLayout.vue";

export default {
    name: "App",
    store,
    mounted() {
        console.log('--- store', Object.keys(this.$store))
        this.$store.dispatch('LOAD_STATS')
    },
    components: {
        MainLayout,
    },
};
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
