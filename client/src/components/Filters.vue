<template lang="pug">
.Filters
    .Filters__caption Filters:
    fieldset.Filters__filter.Filter__filter_occupation
        legend.Filters__filter-legend Occupation time:
        .Filters__filter-container
            FilterButton(
                v-for='f in "rnbqkp"',
                :key='f',
                :active='isFigureSelected(f)',
                size='square-m'
                @click.native='toggleFilter("figure", f)'
            )
                ChessFigure(:figure='f', size='30px')

        .Filters__filter-container
            FilterButton(
                v-for='f in "RNBQKP"',
                :key='f',
                :active='isFigureSelected(f)',
                size='square-m'
                @click.native='toggleFilter("figure", f)'
            )
                ChessFigure(:figure='f', size='30px')
        .Filters__filter-container
            a.Filters__filter-reset(@click='resetFilter("figure")') all
    fieldset.Filters__filter.Filters__filter_winner
        legend.Filters__filter-legend Winner:
        .Filters__filter-container.Filters__not-implemented
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

    fieldset.Filters__filter.Filters__filter_normalize
        //- legend.Filters__filter-legend Normalize data
        .Filters__filter-container
            FilterToggle(
                @click='toggleFilter("normalizeData")'
                :active='filters.normalizeData'
                ) Normalize data

</template>

<script>
import { mapGetters } from 'vuex';
import ChessFigure from './ChessFigure.vue';
import FilterButton from './FilterButton.vue';
import FilterToggle from './FilterToggle.vue';

export default {
    methods: {
        // ...mapActions(['toggleFilter']),
        toggleFilter(filter, value) {
            console.log('-----toggle filter',  filter);
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
        FilterToggle,
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
        padding: 7px
        border: 2px solid #c0c0c070
        border-radius: 15px
        color: gray

    &__filter-controls
        display: block
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

    &__not-implemented
        position: relative
        // opacity: .7
        &:after
            position: absolute
            align-items: top
            justify-content: center
            top: -1em
            left: 0
            right: 0
            bottom: 0
            padding-right: .3em
            background-color: rgba(255, 255, 255, .5)
            content: 'under construction'
            font-size: .8em
            text-align: right

</style>