<template lang="pug">
    .StatsTile 
        .StatsTile__percent(:style="{width: size, height: size}" :title="roundPerc+'%'")
</template>

<script>
export default {
    props: ['percentage'],

    data() {
        return {
            size: '0%'
        };
    },

    mounted() {
        // this.$nextTick doesn't turn animation
        setTimeout(()=>this.size = this.roundPerc + '%');
    },

    computed: {
        roundPerc() { 
            return Math.round(this.percentage * 100) / 100; 
        },
    },

    watch: {
        roundPerc() {
            console.log("---watch", this.roundPerc)
            this.size = this.roundPerc + '%';
        }
    }
}
</script>

<style lang="stylus">
    .StatsTile
        display: flex
        justify-content: center
        align-items: center
        width: 100%
        height: 100%

        &__percent
            width: 0%
            height: 0%
            background: #f36363
            border-radius: 50%
            transition: width 0.5s, height 0.5s


</style>