<template lang="pug">
    div.chess-board
        div.chess-board__content
            .chess-board__letters
                .chess-board__letter(v-for="l in 'abcdedgh'") {{ l }}
            .chess-board__digits
                .chess-board__digit(v-for="l in '87654321'") {{ l }}
            
            .chess-board__tiles
                .chess-board__row(v-for="(d, r) in '87654321'")
                    .chess-board__tile(v-for="(l, c) in 'abcdedgh'" :class="'chess-board__tile_side_' + ((r+c)%2 ? 'dark' : 'light')")
                        .chess-board__tile-content
                            //- |{{r}}:{{c}}
                            ChessFigure(:figure="boardSetup[l+d]" v-if="boardSetup[l+d]")
                
            .chess-board__digits
                .chess-board__digit(v-for="l in '87654321'") {{ l }}
            .chess-board__letters
                .chess-board__letter(v-for="l in 'abcdedgh'") {{ l }}

    </div>
</template>

<script>
import ChessFigure from './ChessFigure'
export default {
    name: 'ChessBoard',
    created() {
        console.log('----chessBoard created');
    },
    data() {
        return {
            boardSetup: {
                'a1': 'rl',
                'b1': 'nl',
                'c1': 'bl',
                'd1': 'ql',
                'e1': 'kl',
                'f1': 'bl',
                'g1': 'nl',
                'h1': 'rl',

                'a2': 'pl',
                'b2': 'pl',
                'c2': 'pl',
                'd2': 'pl',
                'e2': 'pl',
                'f2': 'pl',
                'g2': 'pl',
                'h2': 'pl',

                
                'b8': 'nd',
                'a8': 'rd',
                'c8': 'bd',
                'd8': 'qd',
                'e8': 'kd',
                'f8': 'bd',
                'g8': 'nd',
                'h8': 'rd',
                'a7': 'pd',
                'b7': 'pd',
                'c7': 'pd',
                'd7': 'pd',
                'e7': 'pd',
                'f7': 'pd',
                'g7': 'pd',
                'h7': 'pd',
            }
        }
    },
    components: {
        ChessFigure
    },
    
}
</script>

<style lang="stylus">
    dev-border()
        border: 1px solid silver;

    responsive-square() 
        position: relative
        &:after
            content: ''
            display: block
            padding-bottom: 100%
        &__content, &-content
            position: absolute;
            width: 100%
            height: 100%
    
    .chess-board
        responsive-square()
        width: 50%
        margin: auto
        border: 1px solid silver

        &__content
            display: flex
            flex-wrap: wrap

        &__letters
        &__digits
        &__tiles
            box-sizing: border-box

        &__letters
            width: 100%
            height: 5%
            margin: 0 5%;
            display: flex

        &__letter
            flex-grow: 1
            border: 0px solid silver
            border-right-width: 1px
            &:first-child
                border-left-width: 1px

        &__digits
            width: 5%
            height: 90%
            display: flex
            flex-direction: column

        &__digit
            flex-grow: 1
            border: 0px solid silver
            border-bottom-width: 1px
            &:first-child
                border-top-width: 1px

        &__tiles
            width: 90%
            height: 90%

        &__row
            box-sizing: border-box;
            width: 100%
            height: 12.5%
        
        &__tile
            responsive-square()
            display: inline-block
            box-sizing: border-box
            width: 12.5%
            &:hover
                border-color: red
            
            &_side_dark
                background: #eee;

        &__tile-content
            display: flex
            justify-content: center
            align-items: center



</style>