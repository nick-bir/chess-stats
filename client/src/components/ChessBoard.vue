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
                            ChessFigure(:figure="getFigure(l, d)" v-if="getFigure(l, d)")
                
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
            // boardSetup: {
            //     'a1': 'R',
            //     'b1': 'N',
            //     'c1': 'B',
            //     'd1': 'Q',
            //     'e1': 'K',
            //     'f1': 'B',
            //     'g1': 'N',
            //     'h1': 'R',
            //     'a2': 'P',
            //     'b2': 'P',
            //     'c2': 'P',
            //     'd2': 'P',
            //     'e2': 'P',
            //     'f2': 'P',
            //     'g2': 'P',
            //     'h2': 'P',
                
            //     'b8': 'n',
            //     'a8': 'r',
            //     'c8': 'b',
            //     'd8': 'q',
            //     'e8': 'k',
            //     'f8': 'b',
            //     'g8': 'n',
            //     'h8': 'r',
            //     'a7': 'p',
            //     'b7': 'p',
            //     'c7': 'p',
            //     'd7': 'p',
            //     'e7': 'p',
            //     'f7': 'p',
            //     'g7': 'p',
            //     'h7': 'p',
            // },
            board: [
            //  ['a','b','c','d','e','f','g','h']
                ['r','n','b','q','k','b','n','r'], // 8
                ['p','p','p','p','p','p','p','p'], // 7
                [' ',' ',' ',' ',' ',' ',' ',' '], // 6
                [' ',' ',' ',' ',' ',' ',' ',' '], // 5
                [' ',' ',' ',' ',' ',' ',' ',' '], // 4
                [' ',' ',' ',' ',' ',' ',' ',' '], // 3
                ['P','P','P','P','P','P','P','P'], // 2
                ['R','N','B','Q','K','B','N','R'], // 1
            ]
        }
    },

    methods: {
        getFigure(l, d) {
            let f = this.board[d-1][l.charCodeAt(0) - 'a'.charCodeAt(0)];
            return f === ' ' ? '' : f;
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
        border: 1px solid #eaeaea

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
            
            &_side_light
                background: #fff;
            &_side_dark
                background: #eee;

        &__tile-content
            display: flex
            justify-content: center
            align-items: center

</style>