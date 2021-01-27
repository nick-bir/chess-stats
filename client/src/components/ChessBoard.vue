<template lang="pug">
.ChessBoard
    .ChessBoard__content
        .ChessBoard__letters
            .ChessBoard__letter(v-for='l in "abcdefgh"') {{ l }}
        .ChessBoard__digits
            .ChessBoard__digit(v-for='l in "87654321"') {{ l }}

        .ChessBoard__tiles
            .ChessBoard__row(v-for='(d, r) in "87654321"')
                .ChessBoard__tile(
                    v-for='(l, c) in "abcdefgh"',
                    :class='"ChessBoard__tile_side_" + ((r + c) % 2 ? "dark" : "light")'
                )
                    .ChessBoard__tile-content 
                        //- ChessFigure(:figure="getFigure(l, d)" v-if="getFigure(l, d)")
                        StatsTile(
                            :percentage='getPercentage(l, d)',
                            v-if='getPercentage(l, d)'
                        )

        .ChessBoard__digits
            .ChessBoard__digit(v-for='l in "87654321"') {{ l }}
        .ChessBoard__letters
            .ChessBoard__letter(v-for='l in "abcdefgh"') {{ l }}

</div>
</template>

<script>
import { mapGetters } from 'vuex';
import ChessFigure from './ChessFigure';
import StatsTile from './StatsTile';

export default {
    name: 'ChessBoard',

    props: {
        stats: {
            default() {
                return {};
            },
        },
    },

    created() {
        // console.log('----chessBoard created');
    },

    data() {
        return {
            board: [
                //  ['a','b','c','d','e','f','g','h']
                ['r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'], // 8
                ['p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'], // 7
                [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '], // 6
                [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '], // 5
                [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '], // 4
                [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '], // 3
                ['P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'], // 2
                ['R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'], // 1
            ],
        };
    },

    computed: {
        ...mapGetters(['filteredStats']),
    },

    methods: {
        getFigure(l, d) {
            let f = this.board[d - 1][l.charCodeAt(0) - 'a'.charCodeAt(0)];
            return f === ' ' ? '' : f;
        },

        getPercentage(l, d) {
            return this.filteredStats[l + d];
        },
    },

    components: {
        ChessFigure,
        StatsTile,
    },
};
</script>

<style lang="stylus">
dev-border()
    border: 1px solid silver

responsive-square()
    position: relative

    &:after
        content: ''
        display: block
        padding-bottom: 100%

    &__content, &-content
        position: absolute
        width: 100%
        height: 100%

.ChessBoard
    responsive-square()
    min-width: 300px
    border: 1px solid #eaeaea
    box-shadow: 0 0 5px silver

    &__content
        display: flex
        flex-wrap: wrap

    &__row
        box-sizing: border-box
        width: 100%
        height: 12.5%

    &__letters, &__digits, &__tiles
        box-sizing: border-box

    &__letters
        width: 100%
        height: 5%
        margin: 0 5%
        display: flex

    &__letter
        flex-grow: 1

    &__digits
        width: 5%
        height: 90%
        display: flex
        flex-direction: column

    &__digit
        flex-grow: 1
        display: flex
        justify-content: center
        align-items: center

    &__tiles
        width: 90%
        height: 90%
        border: 1px solid #eaeaea

    &__tile
        responsive-square()
        display: inline-block
        box-sizing: border-box
        width: 12.5%

        &:hover
            border-color: red

        &_side_light
            background: #fff

        &_side_dark
            // background: #eee;
            background: #edebeb

    &__tile-content
        display: flex
        justify-content: center
        align-items: center
</style>