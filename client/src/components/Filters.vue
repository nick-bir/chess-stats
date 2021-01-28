<template lang="pug">
.Filters
    .Filters__caption Filters:
    fieldset.Filters__filter
        legend.Filters__filter-container Occupation time:
        .Filters__filter-options
            //- ChessFigure.Filters__filter-option(
            //-     v-for='f in "rnbqkp"',
            //-     :key='f',
            //-     :figure='f',
            //-     :class='{ "Filters__filter-option_selected": isFigureSelected(f) }',
            //-     size='30px',
            //-     @click.native='toggleFilter("figure", f)'
            //- )
            FilterButton(
                v-for='f in "rnbqkp"',
                :key='f',
                :active='isFigureSelected(f)',
                square='m'
                @click.native='toggleFilter("figure", f)'
            )
                ChessFigure(:figure='f', size='30px')

        .Filters__filter-options
            //- ChessFigure.Filters__filter-option(
            //-     v-for='f in "RNBQKP"',
            //-     :key='f',
            //-     :class='{ "Filters__filter-option_selected": isFigureSelected(f) }',
            //-     :figure='f',
            //-     size='30px',
            //-     @click.native='toggleFilter("figure", f)'
            //- )
            FilterButton(
                v-for='f in "RNBQKP"',
                :key='f',
                :active='isFigureSelected(f)',
                square='m'
                @click.native='toggleFilter("figure", f)'
            )
                ChessFigure(:figure='f', size='30px')
        .Filters__filter-options
            a.Filters__filter-reset(@click='resetFilter("figure")') all
    fieldset.Filters__filter.Filters__filter_winner
        legend.Filters__filter-container Winner:
        .Filters__filter-options
            FilterButton(
                @click='toggleFilter("winner.side", "black")',
                :active='filters.winner.side == "black"'
            ) Black
            FilterButton(
                @click='toggleFilter("winner.side", "*")',
                :active='!filters.winner.side'
            ) Any
            FilterButton(
                @click='toggleFilter("winner.side", "white")',
                :active='filters.winner.side == "white"'
            ) White
</template>

<script>
import { mapGetters } from 'vuex';
import ChessFigure from './ChessFigure.vue';
import FilterButton from './FilterButton.vue';

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
        ...mapGetters(['filters']),
    },

    components: {
        ChessFigure,
        FilterButton,
    },
};
</script>

<style lang="stylus">
.Filters
    min-width: 200px

    &__caption
        margin-bottom: .2em
        text-align: center
        font-size: 1.3em

    &__filter
        margin-bottom: 5px
        border: 2px solid #c0c0c070
        border-radius: 15px
        color: gray

    &__filter-options
        display: block

    // &__filter-option
    //     border: 1px solid white
    //     border-radius: 5px
    //     opacity: .6
    //     cursor: pointer

        // &:not(:last-child)
        //     margin-right: 2px

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