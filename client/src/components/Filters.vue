<template lang="pug">
    .Filters
        .Filters__caption Filters:
        fieldset.Filters__filter
            legend.Filters__filter-container Occupation time:
            label.Filters__filter-options
                ChessFigure(
                    v-for="f in 'rnbqkp'" 
                    :figure="f" 
                    class='Filters__filter-option' 
                    :class="{'Filters__filter-option_selected': isFigureSelected(f)}" 
                    size="30px" 
                    @click.native="toggleFilter('figure', f)"
                )
            label.Filters__filter-options
                ChessFigure(
                    v-for="f in 'RNBQKP'" 
                    class='Filters__filter-option' 
                    :class="{'Filters__filter-option_selected': isFigureSelected(f)}" 
                    :figure="f" 
                    size="30px" 
                    @click.native="toggleFilter('figure', f)"
                    )
            label.Filters__filter-options
                a.Filters__filter-reset( @click="resetFilter('figure')") all


</template>

<script>
import ChessFigure from './ChessFigure';
import { mapGetters } from 'vuex';

export default {

    methods: {
        // ...mapActions(['toggleFilter']),
        toggleFilter(filter, value) {
            this.$store.dispatch('toggleFilter', { filter, value });
        },

        resetFilter(filter) {
            this.$store.dispatch('resetFilter', { filter });
        },

        isFigureSelected(f) {
            return this.filters.figure.has(f);
        },

    },

    computed: {
        ...mapGetters(['filters'])
    },

    components: {
        ChessFigure
    }
}
</script>

<style lang="stylus">
    .Filters
        min-width: 200px

        &__caption
            margin-bottom: 0.2em
            text-align: center
            font-size: 1.3em

        &__filter
            border: 1px solid silver 
            border-radius: 20px
            color: gray

        &__filter-options
            display: block

        &__filter-option
            border: 1px solid white
            border-radius: 20%
            opacity: .6
            cursor: pointer
            &:not(:last-child)
                margin-right: 2px
            &:hover
                border-color: #ef72d0
            &_selected
                opacity: 1
                border-color: #eab4dd

        &__filter-reset
            display: block
            text-align: right
            padding-right: 10px
            border-radius: 20%
            cursor: pointer
            &:hover
                color: black


</style>