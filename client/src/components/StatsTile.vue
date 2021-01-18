<template lang="pug">
    .StatsTile(:title="roundPerc + '%'")
        .StatsTile__percent(:style="{width: size, height: size}" :title="roundPerc + '%'")
        .StatsTile__text {{ roundPerc }}
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
            position: absolute
            width: 0%
            height: 0%
            background: #f36363
            border-radius: 50%
            // border: 1px solid silver
            transition: width 0.5s, height 0.5s, opacity 0.3s
            z-index: 0
        
        &__text
            position: absolute
            font-size: 0.5em
            opacity: 0.2
            z-index: 0.5
            cursor: default
            transition: opacity 0.3s
        
        &:hover &__percent
            opacity: 0.2
        &:hover &__text
            opacity: 1
            font-weight: bold


</style>